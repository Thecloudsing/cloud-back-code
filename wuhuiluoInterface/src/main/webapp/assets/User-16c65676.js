import{j as i}from"./index-b467bab1.js";import{E as p,u as m}from"./Editable-84dcc714.js";import{a as s}from"./api-5f1978fd.js";import"./index-e377c6e8.js";import"./index-6f80cc29.js";const h=()=>i.jsx(p,{otherColumns:m,request:(r,t,e,a,u,l)=>{t(!0),s({type:"getUserList",body:{url:`/user.do?operate=getUserList&page=${e}&limit=${a}&like=${l||""}`},then(o){r(o.data),u(o.totalCount)},_finally_(){t(!1)}})},update:(r,t,e)=>{e(!0),s({type:"updateUser",enableNotifications:!0,message:"更新成功",body:{url:"/user.do?operate=updateUser",method:"post",params:r},then(a){t(a.data)},_finally_(){e(!1)}})},add:(r,t,e)=>{e(!0),s({type:"register",enableNotifications:!0,message:"添加成功",body:{url:"/user.do?operate=register",method:"post",params:r},then(a){t(a.data)},_finally_(){e(!1)}})},delete:(r,t,e)=>{e(!0),s({type:"delTeacher",enableNotifications:!0,message:"删除成功",body:{url:`/user.do?operate=delUser&id=${r}`},then(a){t(a.data)},_finally_(){e(!1)}})}});export{h as default};
