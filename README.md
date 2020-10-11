#webpage-screenshotter
This project exposes a REST API that accepts a URL as input, validates that the URL points to a webpage, launches Google Chrome, takes a screenshot and saves the image to disk.

To set up the project in your workspace, follow the steps below:
1. Clone the project and import in your Eclipse/IntelliJ workspace as a Maven project.
2. Run WebpageScreenshotApplication.java as a Java application.
3. Check API definition at http://localhost:8080/swagger-ui-custom.html . (Note that you will not be able to test here as the input URL will often contain slashes, which is not supported in OpenAPI)
4. Using any API testing tool (like Postman), make a GET request on http://localhost:8080/url/<your-url-here>.
5. Check response messages for valid and invalid URL inputs.
