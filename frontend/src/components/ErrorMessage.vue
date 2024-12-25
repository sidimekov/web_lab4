
<script>
export default {
  props: {
    initialMessage: {
      type: String,
      default: "",
    },
    duration: {
      type: Number,
      default: 5000,
    },
  },
  data() {
    return {
      message: this.initialMessage,
      visible: !!this.initialMessage,
      timeoutId: null,
    };
  },
  methods: {
    showMessage(newMessage) {
      this.message = newMessage;
      this.visible = true;

      if (this.timeoutId) {
        clearTimeout(this.timeoutId);
      }

      this.timeoutId = setTimeout(() => {
        this.hideMessage();
      }, this.duration);
    },
    hideMessage() {
      this.visible = false;
      if (this.timeoutId) {
        clearTimeout(this.timeoutId);
      }
    },
  },
};
</script>

<template>
  <transition name="fade">
    <div v-if="visible" class="error-message">
      <span>{{ message }}</span>
      <button class="close-button" @click="hideMessage">×</button>
    </div>
  </transition>
</template>

<style scoped>
.error-message {
  position: fixed;
  top: 80px;
  right: 20px;
  background-color: #f44336;
  color: white;
  padding: 10px 20px;
  border-radius: 5px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  display: flex;
  align-items: center;
  justify-content: space-between;
  min-width: 250px;
}

.close-button {
  background: none;
  border: none;
  color: white;
  font-size: 16px;
  cursor: pointer;
  margin-left: 10px;
}

.close-button:hover {
  color: #ddd;
}
</style>
