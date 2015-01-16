echo off
D:
SET JAVAFANS_HOME=%cd%

cd %JAVAFANS_HOME%
SET MAVEN_OPTS=-Xms256m -Xmx512m -XX:ReservedCodeCacheSize=64m -XX:MaxPermSize=128m

:mvn_command
ECHO 0-���ɿ�ܵ�eclipse��Ŀ�����ļ�
ECHO 1-���ɿ�ܵ�eclipse��Ŀ�����ļ�,ǿ�Ƹ���������ͬ�汾��jar��
ECHO 2-�����ܴ���,��ִ�в���
ECHO 3-�����ܴ���,ǿ�Ƹ���������ͬ�汾��jar�� ��ִ�в���

set /p isopt=��ѡ�����
if /i "%isopt%"=="0" goto mvn_eclipse
if /i "%isopt%"=="1" goto mvn_eclipse1
if /i "%isopt%"=="2" goto mvn_compile
if /i "%isopt%"=="3" goto mvn_compile1

echo "��Чѡ���ѡ��(0-3)"
goto mvn_command

:mvn_eclipse
	echo [INFO]  ��ʼ����ƽ̨���eclipse�ļ�
	cd %JAVAFANS_HOME%
	call mvn clean eclipse:eclipse
	goto mvn_end

:mvn_eclipse1
	echo [INFO]  ��ʼ����ƽ̨���eclipse�ļ� ǿ�Ƹ�����ͬ�汾��jar��
	cd %JAVAFANS_HOME%
	call mvn clean -U eclipse:eclipse
	goto mvn_end

:mvn_compile
	echo [INFO]  ��ʼ������Կ��
	cd %JAVAFANS_HOME%
	call mvn clean install  -Dmaven.test.skip=true
	goto mvn_end

:mvn_compile1
	echo [INFO]  ��ʼ������Կ��
	cd %JAVAFANS_HOME%
	call mvn clean -U install  -Dmaven.test.skip=true
	goto mvn_end

:mvn_end

cd %JAVAFANS_HOME%