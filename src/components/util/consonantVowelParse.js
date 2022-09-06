class consVow{

    getConsonantVowel(str) {
        let result = '';
        for(let i = 0; i < str.length; i++){
            let obj = this.getConsonantVowelCharacter(str[i]);
            result += this.convertConsonantVowelToString(obj);
        }
        return result;
    }

    getConsonantVowelCharacter(kor) {
        
        let f1 = kor.match(/[ㄱ-ㅎ]/)
        if(f1 != null){
            return {
                f: f1.input
            }
        }

        const f = ['ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ',
                'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ',
                'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'];
        const s = ['ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ',
                'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ',
                'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'];
        const t = ['', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ',
                'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ',
                'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ',
                'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'];
    
        const ga = 44032;
        let uni = kor.charCodeAt(0);
    
        uni = uni - ga;
    
        let fn = parseInt(uni / 588);
        let sn = parseInt((uni - (fn * 588)) / 28);
        let tn = parseInt(uni % 28);
    
        return {
            f: f[fn],
            s: s[sn],
            t: t[tn]
        };
    }

    convertConsonantVowelToString(obj){
        let result = '';
        for(let key in obj){
            if(obj[key] != null){
                result += obj[key]; 
            }
        }
        return result;
    }

}

export default new consVow;