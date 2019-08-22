FROM bitnami/java:11.0.3-prod
ENV TZ=UTC
ARG APP_NAME
ENV APP_NAME=${APP_NAME}
ENTRYPOINT /opt/${APP_NAME}/bin/run.sh
WORKDIR /opt/${APP_NAME}
COPY target/appassembler/bin/run.sh bin/
RUN chmod 755 bin/run.sh; mkdir -p /var/opt/${APP_NAME}
COPY target/appassembler/lib lib
#VOLUME /var/opt/${APP_NAME}
WORKDIR /var/opt/${APP_NAME}

ARG JENKINS_URL
ARG NODE_NAME
ARG JOB_NAME
ARG BRANCH_NAME
ARG COMMIT_ID
ARG BUILD_ID

LABEL jenkins.url="${JENKINS_URL}"
LABEL node.name="${NODE_NAME}"
LABEL job.name="${JOB_NAME}"
LABEL branch.name="${BRANCH_NAME}"
LABEL commit.id="${COMMIT_ID}"
LABEL build.id="${BUILD_ID}"