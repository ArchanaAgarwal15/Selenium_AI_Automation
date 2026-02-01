# OpenAI Integration Demo Project - Complete Explanation

## Project Overview
This is a Maven-based Java project that demonstrates how to integrate OpenAI's API using the OpenAI Java SDK. The project is configured to make API calls to OpenAI services.

---

## Project Structure

```
demo/
├── pom.xml                          # Maven configuration file
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── example/
│   │               ├── Main.java    # Main application (OpenAI integration)
│   │               └── App.java     # Template "Hello World" (not used)
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── AppTest.java # JUnit test file
└── .vscode/
    └── launch.json                  # VS Code debug configuration
```

---

## Configuration Files

### 1. pom.xml (Maven Configuration)

**Purpose:** Defines project dependencies, build configuration, and Java version.

**Key Settings:**
- **Java Version:** 21 (matches your installed JDK)
- **Project Coordinates:**
  - GroupId: `com.example`
  - ArtifactId: `demo`
  - Version: `1.0-SNAPSHOT`

**Dependencies:**
1. **JUnit 4.11** - Testing framework (scope: test only)
2. **OpenAI Java SDK 4.0.0** - Official OpenAI client library
   - Includes: HTTP client (OkHttp), JSON processing (Jackson), Kotlin stdlib

**Build Plugins:**
- **maven-compiler-plugin** - Compiles Java source code using Java 21
- **maven-assembly-plugin** - Creates fat JAR with all dependencies bundled
- **maven-surefire-plugin** - Runs unit tests

**Comment at bottom:**
```xml
<!-- OpenAI dependency location: C:\Users\agarw\.m2\repository\com\openai -->
```
This shows where Maven stores downloaded OpenAI libraries locally.

---

### 2. launch.json (VS Code Debug Configuration)

**Purpose:** Configures how VS Code runs and debugs your Java application.

**Configuration: "Current File"**
- **Type:** Java application
- **Main Class:** `com.example.Main`
- **Project Name:** `demo`
- **Environment Variables:**
  - `OPENAI_API_KEY`: Your OpenAI API key
  - This is loaded when you run the program from VS Code
  - The API key is read by the program using `System.getenv("OPENAI_API_KEY")`

**Configuration: "App"**
- Runs the App.java template (Hello World)
- Not configured with OpenAI API key

---

## Main Application Code (Main.java)

### Package Declaration
```java
package com.example;
```
- Must match the folder structure: `src/main/java/com/example/`
- Without this, Java cannot find the class

### Imports
```java
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
```

**What each import does:**
1. **OpenAIClient** - Main interface for OpenAI API
2. **OpenAIOkHttpClient** - HTTP implementation using OkHttp library
3. **Response** - Object that holds the API response
4. **ResponseCreateParams** - Builder for creating API request parameters

### Main Method Breakdown

```java
public static void main(String[] args) {
```
Entry point of the application - this runs when you execute the program.

---

#### Step 1: Initialize OpenAI Client
```java
OpenAIClient client = OpenAIOkHttpClient.fromEnv();
```

**What this does:**
- Creates an OpenAI client instance
- `.fromEnv()` reads the `OPENAI_API_KEY` from environment variables
- The API key is automatically loaded from:
  - launch.json when running from VS Code
  - System environment variables when running from terminal

**If API key is missing:** This will throw an error

---

#### Step 2: Build Request Parameters
```java
ResponseCreateParams params = ResponseCreateParams.builder()
    .input("Say this is a test")
    .model("gpt-5-nano")
    .build();
```

**What this does:**
- Uses the Builder pattern to create request parameters
- **`.input("Say this is a test")`** - The prompt/input text sent to OpenAI
- **`.model("gpt-5-nano")`** - Specifies which AI model to use
  - Note: "gpt-5-nano" may not be a valid model name
  - Valid models: "gpt-4", "gpt-3.5-turbo", etc.
- **`.build()`** - Finalizes and creates the params object

---

#### Step 3: Make API Call
```java
Response response = client.responses().create(params);
```

**What this does:**
- Sends HTTP request to OpenAI API
- Passes the parameters (input text + model name)
- Waits for response from OpenAI servers
- Returns a Response object containing:
  - Generated text
  - Token usage
  - Model information
  - Other metadata

**This is a blocking call** - program waits until OpenAI responds

---

#### Step 4: Print Response
```java
System.out.println(response.toString());
```

**What this does:**
- Converts the Response object to a string
- Prints the entire response object structure

**Problem with `.toString()`:**
- Returns raw object representation (technical format)
- Not user-friendly
- Shows all internal fields

**Better alternatives:**
```java
// Get just the text response:
System.out.println(response.output());

// Or if it has choices:
System.out.println(response.choices().get(0).message().content());
```

---

## How the Program Works (Step-by-Step)

1. **Program starts** → `main()` method executes
2. **API Key loaded** → From launch.json or environment variable
3. **OpenAI client created** → Using the API key
4. **Request built** → Input text + model specified
5. **API call made** → HTTP request sent to OpenAI servers
6. **Wait for response** → Program pauses until OpenAI responds
7. **Response received** → AI-generated text comes back
8. **Print to console** → Response displayed
9. **Program ends** → Exits

---

## How to Run the Program

### Method 1: From VS Code (Recommended)
1. Open `Main.java`
2. Press **F5** or click "Run" button
3. Select "Current File" configuration
4. API key is automatically loaded from launch.json
5. Output appears in Debug Console

### Method 2: From Terminal (Using Maven)
```powershell
cd "c:\Archana_Docs\Selenium Framework\Selenium_AI\demo"
mvn clean compile exec:java -Dexec.mainClass=com.example.Main
```

**Prerequisites:**
- Set environment variable first:
  ```powershell
  $env:OPENAI_API_KEY="your-api-key-here"
  ```

### Method 3: Build JAR and Run
```powershell
mvn clean package -DskipTests
java -jar target/demo-1.0-SNAPSHOT-jar-with-dependencies.jar
```

---

## Environment Variables

### OPENAI_API_KEY
**Purpose:** Authenticates your requests to OpenAI API

**Where it's stored:**
1. **In launch.json** (for VS Code debugging)
   - Path: `.vscode/launch.json`
   - Only works when running from VS Code
   
2. **In terminal session** (temporary)
   ```powershell
   $env:OPENAI_API_KEY="sk-proj-..."
   ```
   - Only lasts until terminal closes

3. **In system environment** (permanent)
   - Windows Settings → System → Advanced → Environment Variables
   - Persists across sessions
   - Requires VS Code restart to take effect

---

## Expected Output

When the program runs successfully:

```
Response{id='...', object='response', created=..., model='...', ...}
```

The actual output format depends on the OpenAI API response structure.

---

## Common Issues and Solutions

### Issue 1: ClassNotFoundException: com.example.Main
**Cause:** Missing package declaration in Main.java  
**Solution:** Ensure first line is `package com.example;`

### Issue 2: API key not found
**Cause:** OPENAI_API_KEY environment variable not set  
**Solution:** 
- Check launch.json has the key
- Or set in terminal: `$env:OPENAI_API_KEY="your-key"`

### Issue 3: Model not found error
**Cause:** "gpt-5-nano" is not a valid model name  
**Solution:** Change to valid model like "gpt-4" or "gpt-3.5-turbo"

### Issue 4: Compilation errors (Java version)
**Cause:** Java 21 required but different version installed  
**Solution:** Update pom.xml to match your Java version

---

## Dependencies Explained

### OpenAI Java SDK (version 4.0.0)
**What it includes:**
- **openai-java-client-okhttp** - HTTP client for making API calls
- **openai-java-core** - Core OpenAI functionality
- **Jackson** - JSON serialization/deserialization
- **OkHttp** - Modern HTTP client library
- **Kotlin stdlib** - Required by OpenAI SDK

**Downloaded to:**
```
C:\Users\agarw\.m2\repository\com\openai\openai-java\4.0.0\
```

---

## Security Note

**⚠️ IMPORTANT:** Your API key is currently stored in `launch.json`

**Security Risks:**
- If you commit this to Git, your key is exposed
- Anyone with the key can use your OpenAI credits

**Best Practices:**
1. Add `.vscode/launch.json` to `.gitignore`
2. Or use `.env` file (not committed to Git)
3. Or use system environment variables
4. Never share or commit API keys

---

## Next Steps

1. **Verify API key is valid** - Test by running the program
2. **Change model name** - Use a valid OpenAI model
3. **Fix output method** - Use proper method to get response text
4. **Add error handling** - Catch exceptions for API failures
5. **Add more features** - Try different prompts, models, parameters

---

## File Purpose Summary

| File | Purpose |
|------|---------|
| `pom.xml` | Maven build configuration, dependencies |
| `launch.json` | VS Code run/debug settings, API key storage |
| `Main.java` | Your application code - OpenAI integration |
| `App.java` | Template file (not used, can be deleted) |
| `AppTest.java` | JUnit test template |

---

c

---

**Project Created:** January 30, 2026  
**Java Version:** 21 (OpenJDK Microsoft Build)  
**Maven Version:** (detected automatically)  
**OpenAI SDK:** 4.0.0
