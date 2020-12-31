@echo off
for /f %%a in (config.txt) do set "%%a"
rmdir dist /Q /S
mkdir dist\jdk
echo Copying the JDK from %javapath%
xcopy %javapath% dist\jdk /E /Y /Q
call mvn clean -q
call mvn package -q
move target\monopoly.exe dist
mkdir dist\variation-configs
mkdir dist\saves
xcopy variation-configs dist\variation-configs /E /Y /Q