echo off
D:
SET JAVAFANS_HOME=%cd%

cd %JAVAFANS_HOME%
SET MAVEN_OPTS=-Xms256m -Xmx512m -XX:ReservedCodeCacheSize=64m -XX:MaxPermSize=128m

:mvn_command
ECHO 0-生成框架的eclipse项目工程文件
ECHO 1-生成框架的eclipse项目工程文件,强制更新依赖相同版本的jar包
ECHO 2-编译框架代码,不执行测试
ECHO 3-编译框架代码,强制更新依赖相同版本的jar包 不执行测试

set /p isopt=【选择命令】
if /i "%isopt%"=="0" goto mvn_eclipse
if /i "%isopt%"=="1" goto mvn_eclipse1
if /i "%isopt%"=="2" goto mvn_compile
if /i "%isopt%"=="3" goto mvn_compile1

echo "无效选项，请选择(0-3)"
goto mvn_command

:mvn_eclipse
	echo [INFO]  开始生成平台相关eclipse文件
	cd %JAVAFANS_HOME%
	call mvn clean eclipse:eclipse
	goto mvn_end

:mvn_eclipse1
	echo [INFO]  开始生成平台相关eclipse文件 强制更新相同版本的jar包
	cd %JAVAFANS_HOME%
	call mvn clean -U eclipse:eclipse
	goto mvn_end

:mvn_compile
	echo [INFO]  开始编译测试框架
	cd %JAVAFANS_HOME%
	call mvn clean install  -Dmaven.test.skip=true
	goto mvn_end

:mvn_compile1
	echo [INFO]  开始编译测试框架
	cd %JAVAFANS_HOME%
	call mvn clean -U install  -Dmaven.test.skip=true
	goto mvn_end

:mvn_end

cd %JAVAFANS_HOME%