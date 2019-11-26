pipeline {
    agent { dockerfile true }
    tools { 
        maven 'Maven 3.6.3'  
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn package' 
            } 
        }
		stage('Build Imagem Docker! ') {
			steps {
				sh 'cp /root/.jenkins/workspace/alarum_4.0/target/AlarumAdmin-1.0-SNAPSHOT.war /home/senai/docker/'
				sh 'docker image build -t alarum_4.0/tomcat /home/senai/docker/'
				cd 'docker-tomcat-tutorial'
				sh 'docker build -t mywebapp .'
				sh 'docker run -p 80:8080 mywebapp'
			}
		}
		 
    }
}