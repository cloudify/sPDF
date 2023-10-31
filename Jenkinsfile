pipeline {
  agent {
    kubernetes {
      inheritFrom 'default'
      containerTemplates([
      containerTemplate(name: 'play', image: 'flowdocker/play_builder:latest-java17-jammy', command: 'cat', ttyEnabled: true),
      ])
    }
  }
  
  options { 
    disableConcurrentBuilds() 
  }

  stages {
    stage('Checkout') {
      steps {
        checkoutWithTags scm
      }
    }

    stage('Tag new version') {
      when { branch 'main' }
      steps {
        script {
          VERSION = new flowSemver().calculateSemver()
          new flowSemver().commitSemver(VERSION)
        }
      }
    }

    stage('SBT Test') {
      steps {
        container('play') {
          script {
            try {
              sh '''
                sbt clean test:compile test scalafmtSbtCheck scalafmtCheck
              '''
            } finally {
                junit allowEmptyResults: true, testResults: '**/target/test-reports/*.xml'
            }
          }
        }
      }
    }

    stage('Release') {
      when { branch 'main' }
      steps {
        container('play') {
          withCredentials([
            usernamePassword(
              credentialsId: 'jenkins-x-jfrog',
              usernameVariable: 'ARTIFACTORY_USERNAME',
              passwordVariable: 'ARTIFACTORY_PASSWORD'
            )
          ]) {
            sh 'sbt clean +publish'
            syncDependencyLibrary()
          }
        }
      }
    }
  }
}
