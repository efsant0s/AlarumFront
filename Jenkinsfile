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
				sh 'cp /root/.jenkins/workspace/alarum_master/target/AlarumAdmin-1.0-SNAPSHOT.war /home/senai/docker/'
				sh 'docker image build -t alarum_master/tomcat /home/senai/docker/'
			}
		}
		stage('Remove Container ') {
			steps {
				//sh 'docker container stop trabalho-sidnei'
				sh 'docker container rm -f $(docker container ls -aq)'
			}
		}
		stage('Executar') {
			steps {
				sh 'docker container run -d --name alarum_master --publish 8081:8080 alarum_master/tomcat'
			}
		}
		stage('Remover War pasta Docker ') {
			steps {
				//sh 'sudo rm -f --recursive -r /var/lib/jenkins/workspace/trabalho-sidnei_master'
				sh 'rm -f /home/senai/docker/AlarumAdmin-1.0-SNAPSHOT.war'
			}
		}
    }
}
