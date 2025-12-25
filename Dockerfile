# Jenkins LTS já vem com Java 11
FROM jenkins/jenkins:lts

USER root

# Instala dependências básicas + Maven + Firefox + libs necessárias para Chrome
RUN apt-get update && apt-get install -y \
    wget \
    curl \
    unzip \
    git \
    xvfb \
    gnupg \
    maven \
    firefox-esr \
    libnss3 \
    libxi6 \
    libxrandr2 \
    libasound2 \
    libatk1.0-0 \
    libatk-bridge2.0-0 \
    libgtk-3-0 \
    && rm -rf /var/lib/apt/lists/*

# Adiciona repositório oficial do Google Chrome e instala
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-chrome.gpg \
    && echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-chrome.gpg] http://dl.google.com/linux/chrome/deb/ stable main" \
       > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update \
    && apt-get install -y google-chrome-stable

# Instala ChromeDriver (última versão estável disponível)
RUN DRIVER_VERSION=$(curl -s https://chromedriver.storage.googleapis.com/LATEST_RELEASE) \
    && wget -q https://chromedriver.storage.googleapis.com/${DRIVER_VERSION}/chromedriver_linux64.zip \
    && unzip chromedriver_linux64.zip -d /usr/local/bin \
    && rm chromedriver_linux64.zip \
    && chmod +x /usr/local/bin/chromedriver

# Instala GeckoDriver (fixando versão estável para evitar falhas)
ENV GECKO_VERSION v0.34.0
RUN wget -q https://github.com/mozilla/geckodriver/releases/download/${GECKO_VERSION}/geckodriver-${GECKO_VERSION}-linux64.tar.gz \
    && tar -xzf geckodriver-${GECKO_VERSION}-linux64.tar.gz -C /usr/local/bin \
    && rm geckodriver-${GECKO_VERSION}-linux64.tar.gz \
    && chmod +x /usr/local/bin/geckodriver

# Instala Node.js + Lighthouse + Percy CLI
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash - \
    && apt-get install -y nodejs \
    && npm install -g lighthouse @percy/cli

# Corrige PATH para incluir binários instalados via npm
ENV PATH=$PATH:/usr/local/bin

USER jenkins