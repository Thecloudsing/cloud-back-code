//const addr = location.protocol + '//' + location.host + '/'
const addr = window.location.href

function selUser() {
    const url = addr + 'user.do?operate=getUserList'
    axios.get(url).then(
        require => {
            const userList = require.data.data.userList;
            const tab = document.getElementById("tab")
            for (let i = 0; i < userList.length; i++) {
                const tr = document.createElement("tr")
                const td1 = document.createElement("td")
                const td2 = document.createElement("td")
                const td3 = document.createElement("td")
                const td4 = document.createElement("input")
                const td5 = document.createElement("td")
                const td6 = document.createElement("td")
                const update = document.createElement("button")
                const del = document.createElement("button")
                update.value = '更改密码'
                // update.onclick=change(userList[i].id, td4)
                del.value = '删除用户'
                // del.onclick=delUser(userList[i].id)
                td1.innerText = userList[i].id
                td2.innerText = userList[i].username
                td3.innerText = userList[i].account
                td4.value = userList[i].password
                td5.innerText = userList[i].registerTime
                td4.

                td6.appendChild(update)
                td6.appendChild(del)
                tr.appendChild(td1)
                tr.appendChild(td2)
                tr.appendChild(td3)
                tr.appendChild(td4)
                tr.appendChild(td5)
                tab.appendChild(tr)
            }
            alert(require.data.msg)
        },
        error => {
            console.log(error)
        }

)

}



function register() {
    const name = document.getElementById("name").value;
    const ac = document.getElementById("ac").value;
    const au = document.getElementById("au").value;
    if (ac === '' || au === '' || name === '') {
        alert("账号或密码不能为空")
        return;
    }
    const url = addr + 'user.do?operate=register' +
        '&username=' + name +
        '&account=' + ac +
        '&password=' + au
    axios.get(url).then(
        require => {
            alert(require.data.msg)
        },
        error => {
            alert(error)
        }
    )
}

function change(id, e) {
    const url = addr + 'user.do?operate=updateUser' +
        '&id=' + id// +
    // '&password=' + password
}

function delUser(id) {
    const url = addr + 'user.do?operate=delUser' +
        '&id=' + id
    axios.get(url).then(
        require => {
            alert(require.data.msg)
        },
        error => {
            console.log(error)
        }
    )
}



function login() {
    const account = document.getElementById("account").value;
    const password = document.getElementById("password").value;
    if (account === '' || password === '') {
        alert("账号或密码不能为空")
        return;
    }
    const url = addr + 'user.do?operate=login&account=' + account + '&password=' + password
    axios.get(url).then(
        require => {
            alert(require.data.msg)
        },
        error => {
            alert(error)
        }
    )
}