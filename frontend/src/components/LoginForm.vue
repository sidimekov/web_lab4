<script>
export default {
  name: "LoginForm",

  data() {
    return {
      login: "",
      password: ""
    }
  },

  methods: {
    handleLogin() {
      // login
    }
  }
}
</script>

<template>
  <div id="login-form">
    <h2>ВОЙТИ</h2>
    <form @submit.prevent="handleLogin">
      <div class="form-group">
        <input type="text" placeholder="Логин" id="login-input" required>
      </div>

      <div class="form-group">
        <input type="password" placeholder="Пароль" id="password-input" required>
      </div>

      <input type="submit" value="Войти" class="btn-primary">
    </form>

    <button @click="$emit('switchToRegister')" class="btn-secondary">или зарегистрироваться</button>
  </div>
</template>

<style scoped>
  #login-form {
    max-width: 400px;
    margin: 40px auto;
    padding: 30px;
    background: linear-gradient(145deg, #1f1f2e, #252537);
    border-radius: 12px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.5);
    text-align: center;
    color: #e0e0ff;
    animation: fadeIn 1s ease-in-out;
  }
  h2 {
    margin-bottom: 20px;
    font-size: 1.8em;
    font-weight: bold;
    color: #7f7fff;
  }

  .form-group {
    margin-bottom: 20px;
    text-align: left;
  }

  label {
    display: block;
    margin-bottom: 8px;
    font-size: 1em;
    color: #b0b0ff;
  }

  input {
    width: 100%;
    padding: 10px;
    font-size: 1em;
    border: none;
    border-radius: 8px;
    background: rgba(255, 255, 255, 0.1);
    color: #ffffff;
    outline: none;
    box-shadow: inset 0 2px 5px rgba(0, 0, 0, 0.3);
    transition: background 0.3s ease;
  }

  input:focus {
    background: rgba(255, 255, 255, 0.2);
  }

  .btn-primary {
    display: block;
    width: 100%;
    padding: 10px;
    margin: 15px 0;
    background: linear-gradient(145deg, #7f7fff, #5f5fff);
    border: none;
    border-radius: 8px;
    color: #ffffff;
    font-size: 1em;
    cursor: pointer;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    box-shadow: 0 4px 10px rgba(127, 127, 255, 0.5);
  }

  .btn-primary:hover {
    transform: translateY(-2px);
    box-shadow: 0 6px 15px rgba(127, 127, 255, 0.7);
  }

  .btn-secondary {
    display: block;
    width: 100%;
    padding: 10px;
    background: linear-gradient(145deg, #5f5fff, #3f3fff);
    border: none;
    border-radius: 8px;
    color: #ffffff;
    font-size: 1em;
    cursor: pointer;
    transition: background 0.3s ease;
  }

  .btn-secondary:hover {
    background: linear-gradient(145deg, #3f3fff, #5f5fff);
  }

  @keyframes fadeIn {
    from {
      opacity: 0;
      transform: translateY(-20px);
    }
    to {
      opacity: 1;
      transform: translateY(0);
    }
  }
</style>