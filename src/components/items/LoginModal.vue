<template>
    <div class="login-modal" v-bind:class="{show:isVisable}">
        <div class="modal-body">
            <div class="exit-btn">
                <span @click="exitClick">x</span></div>
            <div class="modal-inner">
                <div class="id-input">
                    <input placeholder="아이디" v-model="username">
                </div>
                <div class="pw-input">
                    <input type="password" placeholder="비밀번호" v-model="password">
                </div>
                <div class="login-btn">
                    <button @click="loginBtnClick">로그인</button>
                </div>
                <div class="kakao-login">
                    <a id="kakao-login-btn"></a>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import axiosUrlChange from '../util/axiosUrlChange';

export default {
    data(){
        return{
            username:'',
            password:''
        }
    },
    props:['isVisable'],
    methods:{
        fetchLogin(url){
            const vue = this;

            let form = new FormData();
            form.append('username', this.username)
            form.append('password', this.password)

            axios.post(url, form, {withCredentials: true})
            .then(function(response) {
                console.log(response)
                vue.loginModalClose();
            }).catch(()=>{
                console.log('eerorr')
            })
        },
        loginModalClose(){
            this.$emit('exitClick');
        },
        exitClick(){
            this.loginModalClose();
        },
        loginBtnClick(){
            
            this.fetchLogin(axiosUrlChange.currentLocationUrl('api/users/login'));
            
        },
        kakaoLogin(access_token, username){
            let url = axiosUrlChange.currentLocationUrl('api/users/login')

            const vue = this;

            let form = new FormData();
            form.append('password', access_token)
            form.append('username', username)

            axios.post(url, form, {withCredentials: true})
            .then(function() {
                vue.loginModalClose();
            }).catch(()=>{
            })
        }
    },
    updated(){
        let vue = this;
        if(window.Kakao.isInitialized() == false){
            window.Kakao.init('21fdda51396c4fa6baa9d79754919f17');

            window.Kakao.Auth.createLoginButton({
                container: '#kakao-login-btn',
                success: async function (authObj) {

                    let resopnse = await axios.get('https://kapi.kakao.com/v2/user/me', {
                        headers : {
                            Authorization : `Bearer ${authObj.access_token}`
                        }
                    });
                    let username = resopnse.data.kakao_account.profile.nickname + '-' + resopnse.data.id;

                    vue.kakaoLogin(authObj.access_token, username);
                },
                fail: function (err) {
                    alert(JSON.stringify(err));
                }
            });
        }
    }
}
</script>
<style lang="scss">
    .login-modal{
        position: absolute;
        z-index : 2;
        top: 0;
        left: 0;

        width: 100%;
        height: 100%;

        display: none;

        background-color: rgba(0, 0, 0, 0.4);
        &.show {
            display: block;
        }

        .modal-body {
            position: absolute;
            top: 50%;
            left: 50%;

            width: 300px;
            height: 450px;


            text-align: center;

            background-color: rgb(255, 255, 255);
            border-radius: 10px;
            box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);

            transform: translateX(-50%) translateY(-50%);
            .modal-inner{
                display:flex;
                flex-direction: column;
            }
        }


    }



    
</style>