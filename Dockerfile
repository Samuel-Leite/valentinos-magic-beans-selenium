# Jenkins LTS já vem com Java 11
FROM jenkins/jenkins:lts

USER root

# Instala dependências básicas + Maven + Firefox
RUN apt-get update && apt-get install -y \
    wget \
    curl \
    unzip \
    git \
    xvfb \
    gnupg \
    maven \
    firefox-esr \
    && rm -rf /var/lib/apt/lists/*

# Adiciona repositório oficial do Google Chrome e instala (sem apt-key)
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-chrome.gpg \
    && echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-chrome.gpg] http://dl.google.com/linux/chrome/deb/ stable main" \
       > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update \
    && apt-get install -y google-chrome-stable

# Instala ChromeDriver compatível
RUN DRIVER_VERSION=$(curl -s "https://chromedriver.storage.googleapis.com/LATEST_RELEASE") && \
    wget -q https://chromedriver.storage.googleapis.com/${DRIVER_VERSION}/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip -d /usr/local/bin && \
    rm chromedriver_linux64.zip && \
    chmod +x /usr/local/bin/chromedriver

# Instala GeckoDriver (para Firefox)
RUN GECKO_VERSION=$(curl -s https://api.github.com/repos/mozilla/geckodriver/releases/latest | grep "tag_name" | cut -d '"' -f4) && \
    wget -q https://github.com/mozilla/geckodriver/releases/download/${GECKO_VERSION}/geckodriver-${GECKO_VERSION}-linux64.tar.gz && \
    tar -xzf geckodriver-*-linux64.tar.gz -C /usr/local/bin && \
    rm geckodriver-*-linux64.tar.gz && \
    chmod +x /usr/local/bin/geckodriver

USER jenkins