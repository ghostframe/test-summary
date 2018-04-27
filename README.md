# test-summary
A maven plugin that generates web views of test classes, grouping test cases by method tested.

## Usage
In your `pom.xml`:
- Add the plugin to the build lifecycle:
```xml
<build>
  <plugins>
    ...
    <plugin>
      <groupId>com.ghostframe</groupId>
      <artifactId>test-summary-maven-plugin</artifactId>
      <version>1.0.0</version>
      <configuration>
          <outputDirectory>target\test-summary\</outputDirectory>
      </configuration>
      <executions>
          <execution>
              <phase>test</phase>
              <goals>
                  <goal>generate</goal>
              </goals>
          </execution>
      </executions>
    </plugin>
    ...
  </plugins>
</build>
```
- Add this to your pluginRepositories for maven to download it from github:
```xml
<pluginRepositories>
  <pluginRepository>
    <id>test-case-summary</id>
    <url>https://raw.githubusercontent.com/ghostframe/test-summary/master/maven-repository/</url>
  </pluginRepository>
</pluginRepositories>
```
- Run the `test` goal

## What it does
### Given this:
```java
package com.myproject;

public class TestClass {

    @Test
    public void method1_withScenarioA_returnsValueA() {
      //...
    }

    @Test
    public void method1_shouldSupportNulls() {
      //...
    }

    @Test
    public void method2_withScenarioA_returnsValueA() {
      //...
    }
    
    @Test
    public void anotherTest() {
      //...
    }

}
```
### It will output `target\test-summary\com\myproject\TestClass.html`:
> #### TestClass
> ##### method1
> - With scenario a &rarr; returns value a
> - Should support nulls
> ##### method2
> - With scenario a &rarr; returns value a
> ##### anotherTest
