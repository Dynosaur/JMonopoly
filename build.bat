@echo off
rmdir dist /Q /S
mkdir dist\jdk
xcopy E:\Lang\jdk-15 dist\jdk /E /Y /Q
call mvn clean
call mvn package
move target\monopoly.exe dist
mkdir dist\variation-configs
mkdir dist\saves
xcopy variation-configs dist\variation-configs /E /Y /Q