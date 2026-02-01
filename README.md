# Selenium AI Automation

A Maven-based Java project that demonstrates automated test case generation using OpenAI's GPT API integrated with Selenium WebDriver and TestNG framework.

## Features

- **AI-Powered Test Generation**: Automatically generates Selenium test code from user stories using OpenAI's API
- **Selenium WebDriver Integration**: Full support for browser automation
- **TestNG Framework**: Test execution and reporting
- **ExtentReports**: Comprehensive test reporting with screenshots
- **OpenAI Integration**: Uses GPT-4.1-mini model for intelligent test case generation

## Project Structure

```
demo/
├── pom.xml                                      # Maven configuration
├── src/
│   ├── main/java/com/example/
│   │   ├── Config.java                         # Configuration loader
│   │   ├── Main.java                           # Simple OpenAI test
│   │   ├── SimpleTestcasegeneration.java       # Basic test generation
│   │   ├── TestAutomationGenerator.java        # Advanced test generator
│   │   ├── GenerateAutomatedTestFromStory.java # Story-based generation
│   │   └── OpenAIClientWrapper.java            # OpenAI API wrapper
│   └── test/java/com/example/
│       ├── OrangeHRMLoginTest.java             # Generated test example
│       ├── OrangeHrmLoginTests.java
│       ├── config.properties                   # API key configuration
│       └── config.properties.example           # Configuration template
├── generated-tests/                            # Auto-generated test files
├── PROJECT_EXPLANATION.md                      # Detailed documentation
└── README.md
```

## Prerequisites

- Java 21 (OpenJDK or similar)
- Maven 3.6+
- OpenAI API Key
- ChromeDriver (for Selenium tests)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/ArchanaAgarwal15/Selenium_AI_Automation.git
cd Selenium_AI_Automation/demo
```

2. Configure API Key:
```bash
# Copy the example config
cp src/test/java/com/example/config.properties.example src/test/java/com/example/config.properties

# Edit config.properties and add your OpenAI API key
# OPENAI_API_KEY=your-api-key-here
```

Or set environment variable:
```bash
export OPENAI_API_KEY="your-api-key-here"
```

## Usage

### Generate Selenium Tests from User Story

```bash
mvn clean compile exec:java -Dexec.mainClass=com.example.GenerateAutomatedTestFromStory
```

### Run Generated Tests

```bash
mvn test -Dtest=OrangeHRMLoginTest
```

### Build Project

```bash
mvn clean compile
```

### Package as JAR

```bash
mvn clean package -DskipTests
```

## Configuration

### config.properties

```properties
OPENAI_API_KEY=your-openai-api-key
```

### Models Supported

- `gpt-4.1-mini` - Lightweight, cost-effective model (default)
- `gpt-4` - Advanced model for complex scenarios
- `gpt-3.5-turbo` - Budget-friendly alternative

## Classes Overview

### Config.java
Loads API key from multiple sources:
- config.properties file
- Environment variables
- Classpath resources

### OpenAIClientWrapper.java
Wrapper for OpenAI API interactions:
- `generateCodeFromStory()` - Generates Java test code
- `generateCodeWithExtentReports()` - Generates tests with reporting
- `saveToFile()` - Saves generated code to files

### GenerateAutomatedTestFromStory.java
Main entry point for generating tests from user stories.

### SimpleTestcasegeneration.java
Simple test case generation using OpenAI API.

### TestAutomationGenerator.java
Advanced test case generator with JSON parsing.

## Example Output

The tool generates complete Selenium test classes with:
- ChromeDriver setup/teardown
- WebDriverWait for explicit waits
- TestNG annotations (@Test, @BeforeClass, @AfterClass)
- Assertions and validations
- Screenshot capture on failures
- ExtentReports integration

## Security Notes

⚠️ **IMPORTANT**: Never commit your API key to version control

- Add sensitive files to `.gitignore`
- Use environment variables for production
- Use `config.properties.example` as a template
- Keep API keys in local config files only

## Testing

Run all tests:
```bash
mvn test
```

Run specific test:
```bash
mvn test -Dtest=OrangeHRMLoginTest
```

## Known Limitations

- ChromeDriver must be in system PATH or specified
- Only Chrome browser supported currently
- Test generation quality depends on user story clarity
- API calls may incur costs based on OpenAI pricing

## Troubleshooting

### API Key Not Found
```bash
# Set environment variable
export OPENAI_API_KEY="your-key"

# Or verify config.properties exists and has key
cat src/test/java/com/example/config.properties
```

### Maven Compilation Errors
- Ensure Java 21 is installed: `java -version`
- Update Maven: `mvn -v`
- Clean and rebuild: `mvn clean compile`

### ChromeDriver Issues
- Download ChromeDriver matching your Chrome version
- Add to system PATH or specify in code
- Ensure no port conflicts (default: 9515)

## Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## License

This project is licensed under the MIT License - see LICENSE file for details.

## Support

For issues and questions:
- GitHub Issues: [Create an issue](https://github.com/ArchanaAgarwal15/Selenium_AI_Automation/issues)
- Documentation: See [PROJECT_EXPLANATION.md](PROJECT_EXPLANATION.md)

## Acknowledgments

- OpenAI for GPT API
- Selenium WebDriver team
- TestNG framework
- ExtentReports library

---

**Created**: February 1, 2026  
**Author**: Archana Agarwal  
**Java Version**: 21  
**Maven Version**: 3.6+
