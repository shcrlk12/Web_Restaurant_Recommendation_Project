class axiosUrlChange{
    
    constructor(){
        this.proxyPort = '1110';
    }
    currentLocationUrl(url){
        let result;

        if(url[0] == '/')
            url = url.substr(1);   
        if(window.location.href.includes('localhost') || window.location.href.includes('127.0.0.1')){
            result = `http://localhost:${this.proxyPort}/${url}`;
        }
        else{
            result = `http://115.139.45.137:${this.proxyPort}/${url}`;
        }

        return result;
    }
}

export default new axiosUrlChange;