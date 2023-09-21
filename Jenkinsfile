pipeline {
  agent {
    kubernetes {
      inheritFrom 'default-dependence-day'
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

    stage('Release') {
      when { branch 'main' }
      steps {
        container('dd') {
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
