const URL = window.location.href
const filename = "index.html"
const reg = new RegExp('^(.*)\/(' + filename + ')?')
const addr = reg.exec(URL)[1] + '/user.do'

Vue.config.productionTip = false
const vm = new Vue({
    el:'#root',
    data:{
        account: '',
        password: '',
        operate: 'login'
    },
    methods: {
        login() {
            if (this.account === '' ||
                this.password === '') {
                alert("账号或密码不能为空")
                return;
            }
            const url = addr +
                '?operate=' + this.operate +
                '&account=' + this.account +
                '&password=' + this.password
            axios.get(url).then(
                require => {
                    console.log(require.data)
                    alert(require.data.message)
                    if (require.data.retCode === 200)
                        window.location.href="management.html"
                },
                error => {
                    alert(error)
                })
        }
    }
})