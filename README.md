# JMonopoly
## Description
A console based application that keeps track of players and their funds. Intended to be used alongside a game of monopoly. Consider this a software version of the
Electronic Banking monopoly's "banking unit".
## Requirements
- Windows
- Maven
- Java 15
## Scripts
- run - simply runs the code without any compilation. Make sure to compile before using this.
- compilerun - compiles and runs the code. Do this after making any changes to the source code.
- build - Creates a distributable version, including the JDK, an exe, and any variation configs you might have.
This is all to allow the application to run on a machine without a JRE.
## Usage
You *must* have Java 15.
### Running on a machine with Java 15
Meaning, you are going to do everything (Including running the app) on a machine with Java 15.
1. Call compilerun.bat to compile and run the application. If you make no changes to the source code, you may also use run.bat after the first compilation.
However, if you do change any source code, make sure to use compilerun.bat so that the changes are actually compiled.
### Running on a machine without Java 15
Meaning, you are building it on a machine with Java 15, but are going to run it somewhere else. You cannot build this app without Java 15.
1. Inside `config.txt`, change javapath to the directory where your JDK is located (For example, `C:\Program Files\java\jdk-15`)
2. Call `build.bat`. This will copy your JDK to the `dist` folder, as well as create an executable version of the application.
3. As long as you copy both `jdk` and `monopoly.exe` (Which are inside the `dist` directory) together, it should run fine on any other Windows machine.
(Although not guaranteed, I've only tested it on one other Windows 10 machine).
### Commands
Shortcuts are highlighted as such: (h)elp. This means one can enter `h` and it will have the same effect as `help`.
- (h)elp - At most points in the application, you may call the help command to view a list of available commands.
- e(x)it - Depending on context, will either exit the application entirely (In the main menu) or exit a game (When in a game).
