# Contact Information Extractor

This project is a Java-based application that extracts phone numbers and email addresses from a given text input using two strategies: `HUMAN` and `BOT`. The `BOT` strategy leverages OpenAI's API to identify and extract contact information, while the `HUMAN` strategy uses predefined parsing rules.

## Features

- **Two Extraction Strategies**:
  - **HUMAN**: Extracts contact details (email and phone number) using predefined regex patterns.
  - **BOT**: Utilizes OpenAI's API for natural language processing to extract contact information from the provided text.
- **RESTful API**: Exposes an endpoint for extracting contact information based on the selected strategy.
- **Error Handling**: Includes retries for rate-limited requests when interacting with OpenAI’s API.

## Prerequisites

- Java 11+
- Maven
- OpenAI API Key

## Project Structure

- **config**: Configuration settings, including OpenAI API key setup.
- **models**: Data models for representing contact information and request data.
- **caller**: Contains the core logic to call OpenAI API (`BOT` strategy) and regex-based logic for `HUMAN` strategy.
- **controller**: REST API controllers for handling requests.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/contact-info-extractor.git
   cd contact-info-extractor
2. Add your OpenAI API key in the application.properties file:
   openai.api.key=your_openai_api_key
3. Build the project: mvn clean install
4. Run the application: mvn spring-boot:run

**Usage**
The project exposes a REST API at http://localhost:8080/smart-contact-assistant/extract. Send a POST request with the following JSON payload:
{
    "address": "Sample address text with email and phone number",
    "extractor": "BOT"
}
**address**: The text containing the address from which to extract contact information.
**extractor**: Selects the extraction strategy (HUMAN or BOT).
**Sample Response**:
{
    "phoneNumber": "1234567890",
    "emailAddress": "example@example.com"
}

**Code Overview**
OpenAICaller: Uses WebClient to interact with OpenAI’s API for the BOT strategy.
ServiceConfig: Configuration class to manage settings, including the OpenAI API key.
ContactInformation: Model class for the extracted contact information.

**Application Logic**:
The HUMAN strategy uses regex patterns to detect phone numbers and email addresses.
The BOT strategy sends the input text to OpenAI’s API and parses the returned data for contact information.
Error Handling and Retry Logic
Custom error messages are returned for unexpected responses or parsing errors.

**Testing**
To test the application:
Run the application locally.

Use tools like Postman or curl to make requests to http://localhost:8080/smart-contact-assistant/extract.


