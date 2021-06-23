<div align="center">
    <img src="github/spring-framework.png" alt="Spring Framework">
</div>
</p>
<div align="center">
    <img src="https://img.shields.io/badge/-Java-red?style=for-the-badge" alt="Java">
    <img src="https://img.shields.io/badge/-SpringBoot-green?style=for-the-badge" alt="Spring Boot">
    <img src="https://img.shields.io/badge/-Zipkin-orange?style=for-the-badge" alt="Zipkin">
    <img src="https://img.shields.io/badge/-Gradle-blue?style=for-the-badge" alt="Gradle">
</div>

# About
Study about [Spring Cloud Sleuth](https://spring.io/projects/spring-cloud-sleuth) and [Zipkin](https://zipkin.io/pages/quickstart.html)

# Building
- You need clone this repository `$ git clone git@github.com:samuel-cruz/spring-sleuth.git`
- Run zipkin from a docker `$ docker run -itd --name trace -p 9411:9411 openzipkin/zipkin`
- Proxy server
  - `$ cd proxy`
  - `$ gradle bootRun`
- Notification server
  - `$ cd notification`
  - `$ gradle bootRun`

# Request
You can generate the requests using curl or Postman, as shown below.
- `curl --location --request POST 'http://localhost:8081/v1/do/notifications'`
- `curl --location --request GET 'http://localhost:8081/v1/done/notifications'`

or

<div align="center">
  <img src="github/post.png" width="700" alt="post-request">
  </p>
  <img src="github/get.png" width="700" alt="get-request">
</div>

# Monitoring logs
- Open Zipkin in your browser `http://localhost:9411/zipkin`

<div align="center">
  <img src="github/zipkin-home.png" width="700" alt="zipkin-home">
  </p>
  <img src="github/zipkin-post.png" width="700" alt="zipkin-post">
  </p>
  <img src="github/zipkin-get.png" width="700" alt="zipkin-get">
</div>

<p>    
  Made with 🧡 by <a href="https://samuel-cruz.github.io/" target="_blank">Samuel Cruz</a>
</p>
