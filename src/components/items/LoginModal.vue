<template>
    <div class="login-modal" v-bind:class="{show:isVisable}">
        <div class="modal-body">
            <div class="exit-btn">
                <span @click="exitClick">x</span></div>
            <div class="modal-inner">
                <div class="id-input">
                    <input placeholder="아이디">
                </div>
                <div class="pw-input">
                    <input placeholder="비밀번호">
                </div>
                <div class="login-btn">
                    <button>로그인</button>
                </div>
                <div class="kakao-login">
                    <a id="kakao-login-btn"></a>
                </div>
            </div>
        </div>
    </div>
</template>
<script>


export default {
    props:['isVisable'],
    methods:{
        exitClick(){
            this.$emit('exitClick');
        }
    },
    updated(){
        if(window.Kakao.isInitialized() == false){
            window.Kakao.init('21fdda51396c4fa6baa9d79754919f17');

            window.Kakao.Auth.createLoginButton({
                container: '#kakao-login-btn',
                success: function (authObj) {
                    console.log(JSON.stringify(authObj));
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