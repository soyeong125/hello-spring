#!/usr/bin/env bash
# switch.sh : 엔진엑스가 바라보는 스프링 부트를 최신 버전으로 변경

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 Port: $IDLE_PORT"
    echo "> Port 전환"
    # 엔진엑스가 변경할 프록시 주소를 생성한다.
    # 반드시 " 쌍따옴표 처리
    # 사용하지 않으면 $service_url을 그대로 인식하지 못하고 변수를 찾게 된다.
    # a | b 라고 보면 a의 문장을 b의 경로에 덮어쓴다.
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc

    echo "> 엔진엑스 Reload"
    # 엔진엑스 설정을 다시 불러온다
    # restart와는 다르다.
    # restart는 끊김이 있지만 reload는 끊김이 없이 다시 불러온다.
    # 중요한 설정은 restart로 반영해야한다. 하지만 외부 설정인 service-url을 다시 불러오기 때문에 reload 처리
    sudo service nginx reload
}