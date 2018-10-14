# hst-diagnostics-extension
HST Page Diagnostics extension

# USAGE

Add the following dependency to your site  project (Or a dependency of your site project)

```xml
<dependency>
  <groupId>nl.openweb.hippo</groupId>
  <artifactId>hst-diagnostics-extension</artifactId>
  <version>1.0.0-SNAPSHOT</version>
  <scope>compile</scope>
</dependency>
```

```xml
<profile>
  <id>profile</id>
  <dependencies>
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjrt</artifactId>
      <version>${aspectjweaver.version}</version>
      <scope>compile</scope>
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
