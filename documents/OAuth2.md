OAuth2 认证流程
=======
获取 authorization code
-------
使用 GET 方式请求以下 URL
```
https://www.douban.com/service/auth2/auth?client_id=0abda2e1d3262fea2038e8a579728fbe&redirect_uri=http://Ryeeeeee.com&response_type=code&scope=shuo_basic_r,shuo_basic_w,douban_basic_common
```
用户点击授权之后，URL 重定向到 redirect_uri 指向的地址，例如：
```
http://ryeeeeee.com/?code=45a2d35f74338b3e
```
code 参数的值即为 authorization code

使用 authorization code 换取 access token
-------
使用 POST 的方式请求如下 URL, 此URL附带了上一步中获取到的 authorization code
```
https://www.douban.com/service/auth2/token?client_id=0abda2e1d3262fea2038e8a579728fbe&redirect_uri=http://Ryeeeeee.com&client_secret=9196f7a84f90c966&grant_type=authorization_code&code=45a2d35f74338b3e
```
返回的 json 串中附带了 access token 以及相关的有效日期，用户 id 和 refresh token 
```
{
  "access_token":"a14afef0f66fcffce3e0fcd2e34f6ff4",
  "expires_in":3920,
  "refresh_token":"5d633d136b6d56a41829b73a424803ec",
  "douban_user_id":"1221"
}
```

使用 refresh token 换取 access token
-------
因为 access token 不是长期有效的，那么当它失效的时候，就有两种方式可供选择

* 第一种就是重新走一遍上面的验证流程

* 也是比较推荐的一种方式，就是用同失效的 access token 一并返回的 refresh token 来换取 access token, 需要注意的是 refresh token 只有在 access token 过期的时候才能使用，而且在换取了 access token 之后，旧的 refresh token 就无法使用了。这样就可以简化再次的认证流程。

```
https://www.douban.com/service/auth2/token?client_id=0abda2e1d3262fea2038e8a579728fbe&client_secret=9196f7a84f90c966&redirect_uri=http://Ryeeeeee.com&grant_type=refresh_token&refresh_token=5d633d136b6d56a41829b73a424803ec
```
