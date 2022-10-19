# Home Control System Demo

## Requirements

- OpenJDK 11
  - [AdoptOpenJDK](https://adoptopenjdk.net) - binary distribution
    - OpenJDK 11 (LTS)
    - JVM: HotSpot
  - or the Java distribution of your Linux distribution
- [Maven](https://maven.apache.org)
- [Eclipse IDE](https://www.eclipse.org) 2021-09+
  - [*e(fx)clipse* plugin](https://www.eclipse.org/efxclipse)
    - Help → Eclipse Marketplace…
  - [projectlombok](https://projectlombok.org)
  - *m2e-apt* plugin for [mapstruct](https://mapstruct.org)
    - Help → Eclipse Marketplace…
  - [projectlombok](https://projectlombok.org)
    - Help → Install New Software…
    - Work with: [https://projectlombok.org/p2](https://projectlombok.org/p2))
  - Check if the annotation processing is enabled
    - Preferences → Maven → Annotation Processing
      - Select *Automatically configure JDT APT (build faster, …)* 

## Run JavaFX App

```console
$ mvn clean javafx:run
```

or create a run and a debug configuration in Eclipse: 

- `Run -> Run As -> Maven Build -> New launch configuration`
  - set a name
  - set Base directory: ${project_loc:sgu.template-maven}
  - set Maven goal: `clean javafx:run`

- `Run -> Run As -> Maven Build -> New launch configuration`
  - set a  name
  - set Base directory: ${project_loc:sgu.template-maven}
  - set Maven goal: `clean javafx:run@debug`
