<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Account</title>
        <link href="https://fonts.googleapis.com/css?family=ZCOOL+XiaoWei" rel="stylesheet">
        <link href="loginCSS.css" rel="stylesheet" type="text/css">
</head>
  <body> 
 
   <div class="cont">
   <div class="demo">
    
    <div class="login"  >
      <div class="login__check"></div>
      <div class="login__form">
      <form class = login action="login" method="post">
        <div class="login__row">
          <svg class="login__icon name svg-icon" viewBox="0 0 20 20">
            <path d="M0,20 a10,8 0 0,1 20,0z M10,0 a4,4 0 0,1 0,8 a4,4 0 0,1 0,-8" />
          </svg>
          
          <input type="text" class="login__input name" name ="nom" placeholder="Username"/>
          
        </div>
        <div class="login__row">
          <svg class="login__icon pass svg-icon"  viewBox="0 0 20 20">
            <path d="M0,20 20,20 20,8 0,8z M10,13 10,16z M4,8 a6,8 0 0,1 12,0" />
          </svg>
       
          <input type="password" class="login__input pass" name="mot de passe" placeholder="Password"/>
          
        </div>
        
        <button type="submit" class="login__submit" value ="login">login</button>
        </form>
      </div>
    </div>
    </div>
    </div>
    
 </body>     
</html>