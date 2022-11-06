pipeline {
  environment {
    mvnHome = tool 'maven-3.5.2'
    dockerImageTag = "cards_admin${env.BUILD_NUMBER}"
    dockerImage = ''
  }

  agent any
  options {
    skipDefaultCheckout(true)
  }
  stages {
    stage("Clone Repository") {
      steps {
        git 'https://github.com/naicson88/Cards-Admin-MS.git'
      }
    }
    stage("Build") {
      steps {
        script {
          echo 'Pulling...'
          sh "'${mvnHome}/bin/mvn' clean install"
        }
      }
    }

    stage('Sonar scan execution') {
      // Run the sonar scan 'local-sonar' is defined as variable in jenkins config
      steps {
        withSonarQubeEnv(installationName: 'local-sonar') {
          sh "'${mvnHome}/bin/mvn'  verify sonar:sonar -Dintegration-tests.skip=true -Dmaven.test.failure.ignore=true -Dforce.zero.coverage=true"
        }
      }
    }
    // If not work, check Webhook setted in SonarQube: Menu Administration->Configurations->Webhooks
    stage("Sonar scan result check") {
      steps {
        script {
          timeout(time: 2, unit: 'MINUTES') {
            def qg = waitForQualityGate()
            if (qg.status != 'OK') {
              error "Pipeline aborted due to quality gate failure: ${qg.status}"
            }
          }
        }
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          // build docker image
          dockerImage = docker.build("cards_admin:${env.BUILD_NUMBER}")
        }
      }
    }
    stage('Deploy Docker Image') {
      steps {
        script {

          // deploy docker image to nexus

          echo "Docker Image Tag Name: ${dockerImageTag}"

          def inspectExitCode = sh script: "docker service inspect cards_admin", returnStatus: true
		  echo "${inspectExitCode}"
          if (inspectExitCode == 1) {
          	echo "Removing Container..."
            sh "docker stop cards_admin"
            sh "docker rm cards_admin"
          }

          sh "docker run --name cards_admin -d -p 8081:8081 cards_admin:${env.BUILD_NUMBER}"

          // docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
          //    dockerImage.push("${env.BUILD_NUMBER}")
          //      dockerImage.push("latest")
          //  }
        }
      }
    }

  }
}