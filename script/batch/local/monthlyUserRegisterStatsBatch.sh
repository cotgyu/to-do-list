BUILD_REPOSITORY=module-batch/build/libs
REPOSITORY=script/batch/local/
PROJECT_NAME=module-batch

CURRENT_DATE=$(date +"%Y-%m-%d-%H-%mm")

echo "> Build 파일복사"

cp $BUILD_REPOSITORY/module-batch*.jar $REPOSITORY


echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)

echo "현재 구동중인 애플리케이션 pid: $CURRENT_PID"


if [ -z "$CURRENT_PID" ]; then
        echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"


echo "> $JAR_NAME 실행"

nohup java -jar \
        -Dspring.config.location=classpath:/application.yml \
        -Dspring.profiles.active=local \
      $JAR_NAME --job.name=monthlyUserRegisterStatsBatch version=$CURRENT_DATE > script/batch/local/nohup_batch.out 2>&1 &