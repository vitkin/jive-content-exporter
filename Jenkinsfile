#!/usr/bin/env groovy

try {
  node('docker') {
    stage('Checkout') {
      checkout scm
    }

    def mavenDockerImage = docker.image('maven:3.6.1-jdk-11')

    def DOCKER_GID = sh(script: 'stat -c %g /var/run/docker.sock', returnStdout: true).trim()

    mavenDockerImage.inside("--group-add ${DOCKER_GID} -v /var/run/docker.sock:/var/run/docker.sock") {
      withMaven(mavenSettingsConfig: 'saas-community') {
        stage('Clean') {
          sh 'export PATH=$MVN_CMD_DIR:$PATH && mvn clean'
        }

        stage('Package') {
          sh 'export PATH=$MVN_CMD_DIR:$PATH && mvn -Pcreate-docker-image package'
        }
      }
    }
  }
} catch (ex) {
  // If there was an exception thrown, the build failed
  if (currentBuild.result != "ABORTED") {
    // Send e-mail notifications for failed or unstable builds.
    // currentBuild.result must be non-null for this step to work.
    emailext(
        recipientProviders: [
            [$class: 'DevelopersRecipientProvider'],
            [$class: 'RequesterRecipientProvider']],
        subject: "Job '${env.JOB_NAME}' - Build ${env.BUILD_DISPLAY_NAME} - FAILED!",
        body: """<p>Job '${env.JOB_NAME}' - Build ${env.BUILD_DISPLAY_NAME} - FAILED:</p>
                        <p>Check console output &QUOT;<a href='${env.BUILD_URL}'>${env.BUILD_DISPLAY_NAME}</a>&QUOT;
                        to view the results.</p>"""
    )
  }

  // Must re-throw exception to propagate error:
  throw ex
}
