@echo off
setlocal

set SRC_DIR=SimpleOpenScreenPlayer\src\core
set SRC_FILE=SimpleOpenScreenPlayer.java
set CLASS_NAME=SimpleOpenScreenPlayer
set OUTPUT_JAR=SOSP.jar
set MAIN_CLASS=core.SimpleOpenScreenPlayer
set RES_FILE=SimpleOpenScreenPlayer\src\core\sosp.png
set MANIFEST=manifest.mf
set BIN_DIR=bin

if not exist %BIN_DIR% mkdir %BIN_DIR%

javac -d %BIN_DIR% %SRC_DIR%\%SRC_FILE% > nul
if %ERRORLEVEL% neq 0 (
    echo compilation via javac failed!
    exit /b %ERRORLEVEL%
)

echo Main-Class: %MAIN_CLASS% > %MANIFEST%

copy %RES_FILE% %BIN_DIR%\core\sosp.png > nul

jar cfm %OUTPUT_JAR% %MANIFEST% -C %BIN_DIR% .
if %ERRORLEVEL% neq 0 (
    echo JAR creation failed.
    exit /b %ERRORLEVEL%
)

rem Cleanup
del %MANIFEST%
rmdir /s /q %BIN_DIR%

echo Build successful. JAR file created: %OUTPUT_JAR%
endlocal
