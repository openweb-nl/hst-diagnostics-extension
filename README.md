# Introduction
This is a plugin for Hippo ([Hippo CMS](https://www.onehippo.org)) Site Toolkit that enable users to profile their code via annotations.

# USAGE

**Step 1:** Add the following dependency to your project /site/pom.xml file (Or a dependency of your project's site)

```xml
<dependency>
  <groupId>nl.openweb.hippo</groupId>
  <artifactId>hst-diagnostics-extension</artifactId>
  <version>1.0.0</version>
</dependency>
```
**Step 2:** Add the following profile to your project /site/pom.xml file
```xml 
<profile>
  <id>profile</id>
  <dependencies>
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjrt</artifactId>
      <version>${aspectjweaver.version}</version>
    </dependency>
  </dependencies>
  <build>
    <plugins>
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>aspectj-maven-plugin</artifactId>
        <version>1.10</version>
        <configuration>
          <weaveDependencies>
            <weaveDependency>
              <groupId>nl.openweb.hippo</groupId>
              <artifactId>hst-diagnostics-extension</artifactId>
            </weaveDependency>
          </weaveDependencies>
          <complianceLevel>1.8</complianceLevel>
          <source>1.8</source>
          <target>1.8</target>
          <showWeaveInfo>true</showWeaveInfo>
          <verbose>true</verbose>
          <Xlint>ignore</Xlint>
          <encoding>UTF-8</encoding>
        </configuration>
        <executions>
          <execution>
            <goals>
              <goal>compile</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</profile>
```
**Step 3:** Add @Timed annotations to some methods in your project.
```java
@Timed
public List<NewsDocuments> getRelatedNews() {
	// ...
}
```
**Step 4:** Build your project via profile "profile"
```bash
mvn clean install -Pprofile
```

