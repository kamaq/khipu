khipu
========

A web application based on Vaadin framework.

![](https://github.com/kamaq/khipu/blob/master/khipu-ui/src/main/webapp/WEB-INF/images/main/main_image.png)

Template for a full-blown Vaadin application that only requires a Servlet 3.0 container to run (no other JEE dependencies).

***

Features
========

This application includes:

- Managing roles and rights
- i18n support. 
- Multiple themes

Project made with:
- Vaadin framework (Archetype Maven)
- Jasper Reports
- Mozzila PDF.js
- JPA
- MySQL
- Git


Project Structure
=================

The project consists of the following three modules:

- parent project: common metadata and configuration
- khipu-widgetset: widgetset, custom client side code and dependencies to widget add-ons
- khipu-ui: main application module, development time
- khipu-production: module that produces a production mode WAR for deployment

The production mode module recompiles the widgetset (obfuscated, not draft), activates production mode for Vaadin with a context parameter in web.xml and contains a precompiled theme. The ui module WAR contains an unobfuscated widgetset, and is meant to be used at development time only.

Workflow
========

To compile the entire project, run "mvn install" in the parent project.

Other basic workflow steps:

- getting started
- compiling the whole project
  - run "mvn install" in parent project
- developing the application
  - edit code in the ui module
  - run "mvn jetty:run" in ui module
  - open http://localhost:8080/
- developing the theme
  - run the application as above
  - edit the theme in the ui module
  - optional: see below for precompiling the theme
  - reload the application page
- client side changes or add-ons
  - edit code/POM in widgetset module
  - run "mvn install" in widgetset module
  - if a new add-on has an embedded theme, run "mvn vaadin:update-theme" in the ui module
- debugging client side code
  - run "mvn vaadin:run-codeserver" in widgetset module
  - activate Super Dev Mode in the debug window of the application
- creating a production mode war
  - run "mvn -Pproduction package" in the production mode module or in the parent module
- testing the production mode war
  - run "mvn -Pproduction jetty:run-war" in the production mode module


Using a precompiled theme
-------------------------

When developing the UI module, Vaadin can compile the theme on the fly on every
application reload, or the theme can be precompiled to speed up page loads.

To precompile the theme run "mvn vaadin:compile-theme" in the ui module. Note, though,
that once the theme has been precompiled, any theme changes will not be visible until
the next theme compilation or running the "mvn clean" target.

When developing the theme, running the application in the "run" mode (rather than
in "debug") in the IDE can speed up consecutive on-the-fly theme compilations
significantly.

The production module always automatically precompiles the theme for the production WAR.


Screenshots
===========

Login page

![](https://github.com/kamaq/khipu/blob/master/khipu-ui/src/main/webapp/WEB-INF/images/screenshots/khipu_main.jpeg)

Menu Application

![](https://github.com/kamaq/khipu/blob/master/khipu-ui/src/main/webapp/WEB-INF/images/screenshots/khipu_menu.jpeg)

Forms

![](https://github.com/kamaq/khipu/blob/master/khipu-ui/src/main/webapp/WEB-INF/images/screenshots/khipu_forms.jpeg)

Reports

![](https://github.com/kamaq/khipu/blob/master/khipu-ui/src/main/webapp/WEB-INF/images/screenshots/khipu_report.jpeg)

Reports sent by e-mail

![](https://github.com/kamaq/khipu/blob/master/khipu-ui/src/main/webapp/WEB-INF/images/screenshots/khipu_report_mail.jpeg)

Restore Account page

![](https://github.com/kamaq/khipu/blob/master/khipu-ui/src/main/webapp/WEB-INF/images/screenshots/khipu_restore_account.jpeg)


Copyright and license
=====================

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
