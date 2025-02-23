#!pipelineDecission ane dantlo we need to decide which pipeline to call
//Method or function

def decidePipeline(Map configMap){
    application = configMap.get("application")
    switch(application) {
        case 'nodejsVM'
            nodejsVM(configMap)
            break
        case 'javaVM'
            javaVM(configMap)
            break
        case 'nodejsEKS'
            nodejsEKS(configMap)
            break
        default:
            error "Application is not recognised"
            break
    }
}