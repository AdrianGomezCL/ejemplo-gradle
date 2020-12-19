pipeline {
    agent any

    parameters {
        choice(
            name:'compileTool',
            choices: ['Maven', 'Gradle'],
            description: 'Seleccione herramienta de compilacion'
        )
    }

    stages {
        stage('pipeline') {
            steps {
                script {

                    params.compileTool

                    env.STAGE=''

                    switch(params.compileTool)
                    {
                        case 'Maven':
                            def ejecucion = load 'maven.groovy'
                            ejecucion.call()
                        break;
                        case 'Gradle':
                            def ejecucion = load 'gradle.groovy'
                            ejecucion.call()
                        break;
                    }
                }
            }
        }
    }

    post {
        success {
            slackSend color: 'good', message: "[Adrian Gomez] [${env.JOB_NAME}] [${params.compileTool}] ejecución exitosa"
        }
        failure {
            slackSend color: 'danger', message: "[Adrian Gomez] [${env.JOB_NAME}] [${params.compileTool}] ejecucion fallida en stage ${env.STAGE}"
        }
    }
}