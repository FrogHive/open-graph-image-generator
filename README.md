# Open graph image Generator


An Open graph image generator that uses the current web page information to create an Open graph Image.

The code in this project uses the title, subTitle, color, and main image url information to generate an Open graph image for a post as an example.

<br/>

## Open Grpah(og)? 

The Open Graph protocol enables any web page to become a rich object in a social graph.

For more information, read [Open Graph Protocol](https://ogp.me/).

The open graph protocol allows external sites to provide images for links, such as the following example images(github og images), so that users can get information about the page without having to go to the link.

![Open graph example image](https://github.com/KimDoubleB/LAB/assets/37873745/124fe6df-4ff3-42ae-80ce-a415afb669e4)

<br/>

## Why?

In an open graph, images are more visually appealing to users.

It can attract users' attention and encourage them to visit the page further, or it can give them a glimpse of what the page is about without having to visit it.

However, Open graph images are usually served as images that exist on the page. 
For example, the first image on the page, or the largest image, or an image that fits certain rules.

However, in this case, the image cannot describe the page as a whole, and users are unlikely to be intrigued by the little information it provides.

The Open Graph Image Generator project aims to solve this problem by generating Open graph images using page information.

The main idea came from [A framework for building Open Graph images](https://github.blog/2021-06-22-framework-building-open-graph-images/) on Github blog.

<br/>

## Architecture

![Open Graph Image Generator Architecture](https://github.com/KimDoubleB/LAB/assets/37873745/e45f4fbf-22d0-4fe3-9bae-e0f59f69645f)

- nodejs 18.x
- [serverless framework](https://github.com/serverless/serverless)
- [puppeteer](https://github.com/puppeteer/puppeteer) 21.3.7
- [@sparticuz/chromium](https://github.com/Sparticuz/chromium) 117.0.0 (ref: [Chromium Support](https://pptr.dev/chromium-support))
- [eta](https://github.com/eta-dev/eta) 3.1.1
- [aws-sdk](https://github.com/aws/aws-sdk-js) 2.1472.0
- AWS
  - AWS Lambda
  - Amazon S3
  - Amazon API Gateway

### For use

![Use Architecture](https://github.com/KimDoubleB/LAB/assets/37873745/83c98cce-a051-4523-b31e-5bb370a78678)


Set the generated Open graph image in the meta tag.


<br/>

## Example

This is an example using [code written in test](https://github.com/Gdx-kr/open-graph-image-generator/tree/main/test/image.generator) with ngrok.

```
curl -X POST localhost:8080/post?title=hello_og!&category=NEWS&subTitle=og_image_generator:D&mainImageUrl=MainImageUrl
```

<br/>

**Facebook**

![facebook example image](https://github.com/KimDoubleB/LAB/assets/37873745/603957a1-da12-4c7b-a880-281372fecc7d)

<br/>

**Twitter**

![Twitter example image](https://github.com/KimDoubleB/LAB/assets/37873745/89802244-0204-4d2c-ac81-c826f1528b8a)

  


