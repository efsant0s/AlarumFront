pipeline {
    agent any
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
			}
		}
		stage('Executar') {
			steps {
				sh 'docker container run -d --name alarum_4.0 --publish 8081:8080 -it alarum_4.0/tomcat'
			}
		}
		stage('Remover Workspace') {
			steps {
				//sh 'sudo rm -f --recursive -r /var/lib/jenkins/workspace/trabalho-sidnei_master'
				sh 'rm -f /home/senai/docker/AlarumAdmin-1.0-SNAPSHOT.war'
			}
		}
    }
}