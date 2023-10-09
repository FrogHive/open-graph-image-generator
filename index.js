const path = require("path");
const puppeteer = require("puppeteer-core");
const chromium = require("@sparticuz/chromium");
const S3 = require("aws-sdk/clients/s3");
const { Eta } = require("eta");

const eta = new Eta({ views: path.join(__dirname, "templates") });
const s3 = new S3();

const openGraphImageDirectory = process.env.BUCKET_IMAGE_DIRECTORY;
const defaultImage = process.env.DEFAULT_IMAGE;
const imageExtension = process.env.IMAEG_EXTENSION;

async function generateImage(html) {
  const browser = await puppeteer.launch({
    args: chromium.args,
    defaultViewport: chromium.defaultViewport,
    executablePath: await chromium.executablePath(),
    headless: chromium.headless,
  });
  const page = await browser.newPage();
  await page.setContent(html, { waitUntil: "networkidle0" });
  const screenshotBuffer = await page.screenshot({
    clip: {
      width: 1200,
      height: 630,
      x: 0,
      y: 0
    },
    type: imageExtension,
  });

  await page.close();
  return screenshotBuffer;
 }

function getPost(event) {
  const rawBody = event.isBase64Encoded ? Buffer(event.body, "base64").toString() : event.body;
  const body = JSON.parse(rawBody);
	console.log("**Request body:", body);
  return {
    "title": body.title ?? console.error("No Title value was provided"),
    "subTitle": body.subTitle ?? "",
    "color": body.color ?? console.error("No Color value was provided"),
    "image": body.image ?? defaultImage
  };
}

module.exports.handler = async (event) => {
	// capturing open graph image after rendering
  const post = getPost(event);
  const html = eta.render("./open-graph-image", post);
  const openGraphImage = await generateImage(html);

	// upload image to S3
  const fileName = `${post.title}_${post.subTitle}_${post.color}.${imageExtension}`;
  const uploadResult = await s3.upload({
    Bucket: process.env.BUCKET_NAME,
    Key: `${openGraphImageDirectory}/${fileName}`,
    Body: openGraphImage,
		ContentType: `image/${imageExtension}`
  }).promise();

  return {
		statusCode: 200,
		headers: {"content-type": "application/json"},
    body: JSON.stringify(uploadResult),
  };
};
