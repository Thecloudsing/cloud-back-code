const URL = window.location.href
const filename = "management.html"
const reg = new RegExp('^(.*)\/(' + filename + ')?')
const addr = reg.exec(URL)[1] + '/user.do'

Vue.config.productionTip = false
const vm = new Vue({
    el:'#root',
    data:{
        username: '',
        account: '',
        password: '',
        search: '',
        turn: 1,
        currentPage: 1,
        lastPage: -1,
        limit: 10,
        userList: [],
        userTemp: [],
        changeStatus: []
    },
    methods:{
        register() {
            if (this.username === '' ||
                this.account === '' ||
                this.password === '') {
                alert("账号或密码不能为空")
                return;
            }
            const url = addr + '?operate=register' +
                '&id=' + (Math.random() + '').substring(2,10) +
                '&username=' + this.username +
                '&account=' + this.account +
                '&password=' + this.password
            axios.get(url).then(
                require => {
                    this.flush(require)
                },
                error => {
                    alert(error)
                }
            )
        },
        getUserList() {
            const url = addr + '?operate=getUserList' +
                '&like=' + this.search +
                '&page=' + this.currentPage +
                '&limit=' + this.limit
            axios.get(url).then(
                require => {
                    console.log(require.data)
                    this.userList = require.data.data
                    this.lastPage = require.data.lastPage
                    this.currentPage = require.data.currentPage
                    this.userList.forEach((value) => {
                        this.changeStatus.push(false)
                        this.userTemp.push(value.password)
                    })
                },
                error => {
                    alert(error)
                }
            )
        },
        login() {
            window.location.href="index.html"
        },
        change(i,id) {
            this.changeStatus.forEach((value,index) => {
                if (value)
                    this.commit(index,this.userList[index].id)
            })
            this.changeStatus.splice(i,1,!this.changeStatus[i])
        },
        commit(i,id) {
            this.changeStatus.splice(i, 1, !this.changeStatus[i])
            if (this.userList[i].password === this.userTemp[i]) {
                return
            }
            if (this.userTemp[i] === '') {
                alert("输入不能为空")
                return
            }
            const url = addr +
                '?operate=updateUser' +
                '&id=' + id +
                '&password=' + this.userTemp[i]

            axios.get(url).then(
                require => {
                    this.flush(require)
                },
                error => {
                    console.log(error)
                }
            )
        },
        delUser(id) {
            const url = addr +
                '?operate=delUser' +
                '&id=' + id
            axios.get(url).then(
                require => {
                    this.flush(require)
                },
                error => {
                    console.log(error)
                }
            )
        },
        goto(page) {
            this.currentPage = page
            this.getUserList()
        },
        flush(res) {
            console.log(res)
            if (res.data.retCode === 200) {
                this.userList = res.data.data
                this.lastPage = res.data.lastPage
                this.currentPage = res.data.currentPage
                this.userList.forEach((value) => {
                    this.changeStatus.push(false)
                    this.userTemp.push(value.password)
                })
            } else {
                alert(res.data.message)
            }
        },
        turnPage() {
            this.currentPage = this.turn
        }
    },
    watch: {
        // currentPage() {
        //     this.getUserList()
        //     this.turn = this.currentPage
        // }
    },
    beforeMount() {
        this.getUserList();
    }
})
