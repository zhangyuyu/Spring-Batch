@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  SpringBatchExample startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and SPRING_BATCH_EXAMPLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\SpringBatchExample.jar;%APP_HOME%\lib\spring-core-3.2.2.RELEASE.jar;%APP_HOME%\lib\spring-oxm-3.2.2.RELEASE.jar;%APP_HOME%\lib\spring-batch-core-2.2.0.RELEASE.jar;%APP_HOME%\lib\spring-batch-infrastructure-2.2.0.RELEASE.jar;%APP_HOME%\lib\maven-compiler-plugin-2.3.2.jar;%APP_HOME%\lib\spring-jdbc-3.2.2.RELEASE.jar;%APP_HOME%\lib\mysql-connector-java-5.1.25.jar;%APP_HOME%\lib\commons-logging-1.1.1.jar;%APP_HOME%\lib\commons-lang-2.5.jar;%APP_HOME%\lib\spring-beans-3.2.2.RELEASE.jar;%APP_HOME%\lib\xstream-1.3.jar;%APP_HOME%\lib\jettison-1.1.jar;%APP_HOME%\lib\spring-aop-3.2.0.RELEASE.jar;%APP_HOME%\lib\spring-context-3.2.0.RELEASE.jar;%APP_HOME%\lib\spring-retry-1.0.2.RELEASE.jar;%APP_HOME%\lib\maven-plugin-api-2.0.6.jar;%APP_HOME%\lib\maven-artifact-2.0.6.jar;%APP_HOME%\lib\maven-core-2.0.6.jar;%APP_HOME%\lib\maven-toolchain-1.0.jar;%APP_HOME%\lib\plexus-utils-2.0.5.jar;%APP_HOME%\lib\plexus-compiler-api-1.8.1.jar;%APP_HOME%\lib\plexus-compiler-manager-1.8.1.jar;%APP_HOME%\lib\plexus-compiler-javac-1.8.1.jar;%APP_HOME%\lib\xpp3_min-1.1.4c.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\spring-expression-3.2.0.RELEASE.jar;%APP_HOME%\lib\maven-settings-2.0.6.jar;%APP_HOME%\lib\maven-plugin-parameter-documenter-2.0.6.jar;%APP_HOME%\lib\maven-profile-2.0.6.jar;%APP_HOME%\lib\maven-model-2.0.6.jar;%APP_HOME%\lib\wagon-provider-api-1.0-beta-2.jar;%APP_HOME%\lib\maven-repository-metadata-2.0.6.jar;%APP_HOME%\lib\maven-error-diagnostics-2.0.6.jar;%APP_HOME%\lib\maven-project-2.0.6.jar;%APP_HOME%\lib\maven-plugin-descriptor-2.0.6.jar;%APP_HOME%\lib\maven-artifact-manager-2.0.6.jar;%APP_HOME%\lib\maven-monitor-2.0.6.jar;%APP_HOME%\lib\maven-plugin-registry-2.0.6.jar;%APP_HOME%\lib\maven-reporting-api-2.0.6.jar;%APP_HOME%\lib\plexus-container-default-1.0-alpha-9-stable-1.jar;%APP_HOME%\lib\commons-cli-1.0.jar;%APP_HOME%\lib\plexus-interactivity-api-1.0-alpha-4.jar;%APP_HOME%\lib\classworlds-1.1.jar;%APP_HOME%\lib\wagon-file-1.0-beta-2.jar;%APP_HOME%\lib\wagon-http-lightweight-1.0-beta-2.jar;%APP_HOME%\lib\wagon-ssh-external-1.0-beta-2.jar;%APP_HOME%\lib\wagon-ssh-1.0-beta-2.jar;%APP_HOME%\lib\doxia-sink-api-1.0-alpha-7.jar;%APP_HOME%\lib\junit-3.8.1.jar;%APP_HOME%\lib\wagon-http-shared-1.0-beta-2.jar;%APP_HOME%\lib\xml-apis-1.0.b2.jar;%APP_HOME%\lib\wagon-ssh-common-1.0-beta-2.jar;%APP_HOME%\lib\jsch-0.1.27.jar;%APP_HOME%\lib\jtidy-4aug2000r7-dev.jar;%APP_HOME%\lib\plexus-utils-1.0.2.jar;%APP_HOME%\lib\spring-tx-3.2.2.RELEASE.jar

@rem Execute SpringBatchExample
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SPRING_BATCH_EXAMPLE_OPTS%  -classpath "%CLASSPATH%" com.mkyong.App %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SPRING_BATCH_EXAMPLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SPRING_BATCH_EXAMPLE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
