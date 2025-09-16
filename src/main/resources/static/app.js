document.addEventListener('DOMContentLoaded', () => {
  const btn = document.getElementById('reveal-phone');
  const phone = document.getElementById('phone');
  if (btn && phone) {
    btn.addEventListener('click', () => {
      phone.classList.remove('hidden');
      btn.setAttribute('disabled', 'true');
      btn.textContent = 'Telefon ujawniony';
    });
  }
});
