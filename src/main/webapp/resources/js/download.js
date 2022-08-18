function copyClipboard() {
  const text = document.getElementById('text').textContent;
  const textarea = document.createElement('textarea');
  textarea.textContent = text;
  document.body.append(textarea);
  textarea.select();
  document.execCommand('copy');
  textarea.remove();
}

const button = document.getElementById('copyBtn');
button.addEventListener('click', copyClipboard);