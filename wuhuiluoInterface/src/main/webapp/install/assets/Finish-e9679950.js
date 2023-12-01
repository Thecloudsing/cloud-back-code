import{r as n,_ as Y,a as Z,c as _,b as M,d as ee,e as G,g as te,K as ae,f as re,h as B,i as ne,C as W,o as oe,k as H,D as se,j as c}from"./index-7b6c2287.js";import{u as ie,b as le,L as ce,T as A,S as de,F as N,I as L,B as ue,a as pe}from"./input-other-65c56b82.js";var be=["prefixCls","className","style","checked","disabled","defaultChecked","type","onChange"],fe=n.forwardRef(function(e,t){var a,r=e.prefixCls,o=r===void 0?"rc-checkbox":r,C=e.className,f=e.style,h=e.checked,m=e.disabled,u=e.defaultChecked,p=u===void 0?!1:u,y=e.type,w=y===void 0?"checkbox":y,i=e.onChange,j=Y(e,be),v=n.useRef(null),s=ie(p,{value:h}),$=Z(s,2),O=$[0],I=$[1];n.useImperativeHandle(t,function(){return{focus:function(){var d;(d=v.current)===null||d===void 0||d.focus()},blur:function(){var d;(d=v.current)===null||d===void 0||d.blur()},input:v.current}});var S=_(o,C,(a={},M(a,"".concat(o,"-checked"),O),M(a,"".concat(o,"-disabled"),m),a)),b=function(d){m||("checked"in e||I(d.target.checked),i==null||i({target:G(G({},e),{},{type:w,checked:d.target.checked}),stopPropagation:function(){d.stopPropagation()},preventDefault:function(){d.preventDefault()},nativeEvent:d.nativeEvent}))};return n.createElement("span",{className:S,style:f},n.createElement("input",ee({},j,{className:"".concat(o,"-input"),ref:v,onChange:b,disabled:m,checked:!!O,type:w})),n.createElement("span",{className:"".concat(o,"-inner")}))});const he=new ae("antCheckboxEffect",{"0%":{transform:"scale(1)",opacity:.5},"100%":{transform:"scale(1.6)",opacity:0}}),me=e=>{const{checkboxCls:t}=e,a=`${t}-wrapper`;return[{[`${t}-group`]:Object.assign(Object.assign({},B(e)),{display:"inline-flex",flexWrap:"wrap",columnGap:e.marginXS,[`> ${e.antCls}-row`]:{flex:1}}),[a]:Object.assign(Object.assign({},B(e)),{display:"inline-flex",alignItems:"baseline",cursor:"pointer","&:after":{display:"inline-block",width:0,overflow:"hidden",content:"'\\a0'"},[`& + ${a}`]:{marginInlineStart:0},[`&${a}-in-form-item`]:{'input[type="checkbox"]':{width:14,height:14}}}),[t]:Object.assign(Object.assign({},B(e)),{position:"relative",whiteSpace:"nowrap",lineHeight:1,cursor:"pointer",alignSelf:"start",transform:`translate(0, ${e.lineHeight*e.fontSize/2-e.checkboxSize/2}px)`,[`${t}-input`]:{position:"absolute",inset:0,zIndex:1,cursor:"pointer",opacity:0,margin:0,[`&:focus-visible + ${t}-inner`]:Object.assign({},ne(e))},[`${t}-inner`]:{boxSizing:"border-box",position:"relative",top:0,insetInlineStart:0,display:"block",width:e.checkboxSize,height:e.checkboxSize,direction:"ltr",backgroundColor:e.colorBgContainer,border:`${e.lineWidth}px ${e.lineType} ${e.colorBorder}`,borderRadius:e.borderRadiusSM,borderCollapse:"separate",transition:`all ${e.motionDurationSlow}`,"&:after":{boxSizing:"border-box",position:"absolute",top:"50%",insetInlineStart:"21.5%",display:"table",width:e.checkboxSize/14*5,height:e.checkboxSize/14*8,border:`${e.lineWidthBold}px solid ${e.colorWhite}`,borderTop:0,borderInlineStart:0,transform:"rotate(45deg) scale(0) translate(-50%,-50%)",opacity:0,content:'""',transition:`all ${e.motionDurationFast} ${e.motionEaseInBack}, opacity ${e.motionDurationFast}`}},"& + span":{paddingInlineStart:e.paddingXS,paddingInlineEnd:e.paddingXS}})},{[t]:{"&-indeterminate":{[`${t}-inner`]:{"&:after":{top:"50%",insetInlineStart:"50%",width:e.fontSizeLG/2,height:e.fontSizeLG/2,backgroundColor:e.colorPrimary,border:0,transform:"translate(-50%, -50%) scale(1)",opacity:1,content:'""'}}}}},{[`${a}:hover ${t}:after`]:{visibility:"visible"},[`
        ${a}:not(${a}-disabled),
        ${t}:not(${t}-disabled)
      `]:{[`&:hover ${t}-inner`]:{borderColor:e.colorPrimary}},[`${a}:not(${a}-disabled)`]:{[`&:hover ${t}-checked:not(${t}-disabled) ${t}-inner`]:{backgroundColor:e.colorPrimaryHover,borderColor:"transparent"},[`&:hover ${t}-checked:not(${t}-disabled):after`]:{borderColor:e.colorPrimaryHover}}},{[`${t}-checked`]:{[`${t}-inner`]:{backgroundColor:e.colorPrimary,borderColor:e.colorPrimary,"&:after":{opacity:1,transform:"rotate(45deg) scale(1) translate(-50%,-50%)",transition:`all ${e.motionDurationMid} ${e.motionEaseOutBack} ${e.motionDurationFast}`}},"&:after":{position:"absolute",top:0,insetInlineStart:0,width:"100%",height:"100%",borderRadius:e.borderRadiusSM,visibility:"hidden",border:`${e.lineWidthBold}px solid ${e.colorPrimary}`,animationName:he,animationDuration:e.motionDurationSlow,animationTimingFunction:"ease-in-out",animationFillMode:"backwards",content:'""',transition:`all ${e.motionDurationSlow}`}},[`
        ${a}-checked:not(${a}-disabled),
        ${t}-checked:not(${t}-disabled)
      `]:{[`&:hover ${t}-inner`]:{backgroundColor:e.colorPrimaryHover,borderColor:"transparent"},[`&:hover ${t}:after`]:{borderColor:e.colorPrimaryHover}}},{[`${a}-disabled`]:{cursor:"not-allowed"},[`${t}-disabled`]:{[`&, ${t}-input`]:{cursor:"not-allowed",pointerEvents:"none"},[`${t}-inner`]:{background:e.colorBgContainerDisabled,borderColor:e.colorBorder,"&:after":{borderColor:e.colorTextDisabled}},"&:after":{display:"none"},"& + span":{color:e.colorTextDisabled},[`&${t}-indeterminate ${t}-inner::after`]:{background:e.colorTextDisabled}}}]};function ge(e,t){const a=re(t,{checkboxCls:`.${e}`,checkboxSize:t.controlInteractiveSize});return[me(a)]}const X=te("Checkbox",(e,t)=>{let{prefixCls:a}=t;return[ge(a,e)]});var ve=globalThis&&globalThis.__rest||function(e,t){var a={};for(var r in e)Object.prototype.hasOwnProperty.call(e,r)&&t.indexOf(r)<0&&(a[r]=e[r]);if(e!=null&&typeof Object.getOwnPropertySymbols=="function")for(var o=0,r=Object.getOwnPropertySymbols(e);o<r.length;o++)t.indexOf(r[o])<0&&Object.prototype.propertyIsEnumerable.call(e,r[o])&&(a[r[o]]=e[r[o]]);return a};const q=n.createContext(null),xe=(e,t)=>{var{defaultValue:a,children:r,options:o=[],prefixCls:C,className:f,rootClassName:h,style:m,onChange:u}=e,p=ve(e,["defaultValue","children","options","prefixCls","className","rootClassName","style","onChange"]);const{getPrefixCls:y,direction:w}=n.useContext(W),[i,j]=n.useState(p.value||a||[]),[v,s]=n.useState([]);n.useEffect(()=>{"value"in p&&j(p.value||[])},[p.value]);const $=()=>o.map(l=>typeof l=="string"||typeof l=="number"?{label:l,value:l}:l),O=l=>{s(P=>P.filter(E=>E!==l))},I=l=>{s(P=>[].concat(H(P),[l]))},S=l=>{const P=i.indexOf(l.value),E=H(i);P===-1?E.push(l.value):E.splice(P,1),"value"in p||j(E);const V=$();u==null||u(E.filter(z=>v.includes(z)).sort((z,U)=>{const J=V.findIndex(T=>T.value===z),Q=V.findIndex(T=>T.value===U);return J-Q}))},b=y("checkbox",C),x=`${b}-group`,[d,g]=X(b),k=oe(p,["value","disabled"]);o&&o.length>0&&(r=$().map(l=>n.createElement(K,{prefixCls:b,key:l.value.toString(),disabled:"disabled"in l?l.disabled:p.disabled,value:l.value,checked:i.includes(l.value),onChange:l.onChange,className:`${x}-item`,style:l.style},l.label)));const D={toggleOption:S,value:i,disabled:p.disabled,name:p.name,registerValue:I,cancelValue:O},R=_(x,{[`${x}-rtl`]:w==="rtl"},f,h,g);return d(n.createElement("div",Object.assign({className:R,style:m},k,{ref:t}),n.createElement(q.Provider,{value:D},r)))},Ce=n.forwardRef(xe),ye=n.memo(Ce);var $e=globalThis&&globalThis.__rest||function(e,t){var a={};for(var r in e)Object.prototype.hasOwnProperty.call(e,r)&&t.indexOf(r)<0&&(a[r]=e[r]);if(e!=null&&typeof Object.getOwnPropertySymbols=="function")for(var o=0,r=Object.getOwnPropertySymbols(e);o<r.length;o++)t.indexOf(r[o])<0&&Object.prototype.propertyIsEnumerable.call(e,r[o])&&(a[r[o]]=e[r[o]]);return a};const Se=(e,t)=>{var a,{prefixCls:r,className:o,rootClassName:C,children:f,indeterminate:h=!1,style:m,onMouseEnter:u,onMouseLeave:p,skipGroup:y=!1,disabled:w}=e,i=$e(e,["prefixCls","className","rootClassName","children","indeterminate","style","onMouseEnter","onMouseLeave","skipGroup","disabled"]);const{getPrefixCls:j,direction:v}=n.useContext(W),s=n.useContext(q),{isFormItemInput:$}=n.useContext(le),O=n.useContext(se),I=(a=(s==null?void 0:s.disabled)||w)!==null&&a!==void 0?a:O,S=n.useRef(i.value);n.useEffect(()=>{s==null||s.registerValue(i.value)},[]),n.useEffect(()=>{if(!y)return i.value!==S.current&&(s==null||s.cancelValue(S.current),s==null||s.registerValue(i.value),S.current=i.value),()=>s==null?void 0:s.cancelValue(i.value)},[i.value]);const b=j("checkbox",r),[x,d]=X(b),g=Object.assign({},i);s&&!y&&(g.onChange=function(){i.onChange&&i.onChange.apply(i,arguments),s.toggleOption&&s.toggleOption({label:f,value:i.value})},g.name=s.name,g.checked=s.value.includes(i.value));const k=_({[`${b}-wrapper`]:!0,[`${b}-rtl`]:v==="rtl",[`${b}-wrapper-checked`]:g.checked,[`${b}-wrapper-disabled`]:I,[`${b}-wrapper-in-form-item`]:$},o,C,d),D=_({[`${b}-indeterminate`]:h},d),R=h?"mixed":void 0;return x(n.createElement("label",{className:k,style:m,onMouseEnter:u,onMouseLeave:p},n.createElement(fe,Object.assign({"aria-checked":R},g,{prefixCls:b,className:D,disabled:I,ref:t})),f!==void 0&&n.createElement("span",null,f)))},we=n.forwardRef(Se),K=we,F=K;F.Group=ye;F.__ANT_CHECKBOX=!0;const je=F,{Paragraph:Oe}=A,Ie=`

                 完成初始化数据库
                 请及时移除install目录下的文件
                 或者设置不公开
                 确保数据安全
                 请设置网站管理员默认用户名，密码
                 请妥善保管好账号密码
                    
                                                          
                
`,Ne=()=>{const[e,t]=n.useState(""),[a,r]=n.useState(!1),[o,C]=n.useState(""),[f,h]=n.useState(!1),m=u=>{pe({type:"addbase",body:{url:"database.do?operate=addAdministrator",method:"post",params:{username:e,password:o}},then(){u.remember&&(window.localStorage.setItem("username",e),window.localStorage.setItem("password",o))}})};return c.jsx(c.Fragment,{children:c.jsx(ce,{style:{height:"100vh"},children:c.jsx(A,{style:{margin:"auto",width:"500px"},children:c.jsxs(Oe,{children:[c.jsx("pre",{style:{marginBottom:"30px"},children:Ie}),c.jsx(de,{direction:"vertical",size:"large",style:{margin:"auto",width:"500px"},children:c.jsxs(N,{wrapperCol:{span:24},onFinish:m,autoComplete:"off",children:[c.jsx(N.Item,{name:"username",children:c.jsxs("div",{className:"in",children:[c.jsx(L,{className:a||e!==""?"in-input":"",onFocus:()=>r(!0),onBlur:()=>r(!1),required:!0,pattern:"^\\w{6,10}$",value:e,onChange:u=>t(u.target.value)}),c.jsx("span",{children:"管理员用户名"})]})}),c.jsx(N.Item,{name:"password",children:c.jsxs("div",{className:"in",children:[c.jsx(L,{className:f||o!==""?"in-input":"",onFocus:()=>h(!0),onBlur:()=>h(!1),required:!0,pattern:"^\\w{6,16}$",value:o,onChange:u=>C(u.target.value)}),c.jsx("span",{children:"密码"})]})}),c.jsx(N.Item,{name:"remember",valuePropName:"checked",wrapperCol:{offset:0,span:24},children:c.jsx(je,{children:"Remember me"})}),c.jsx(N.Item,{wrapperCol:{offset:9,span:24},children:c.jsx(ue,{type:"primary",htmlType:"submit",children:"确认&前往主页"})})]})})]})})})})};export{Ne as default};
