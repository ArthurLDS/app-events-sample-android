# Events Sicredi

[![Build](https://img.shields.io/static/v1?label=build&message=passing&color=green)]()
[![Kotlin](https://img.shields.io/static/v1?label=kotlin&message=powered&color=00AFF0)]()
[![Koin](https://img.shields.io/static/v1?label=koin&message=2.1.5&color=F68212)]()
[![Glide](https://img.shields.io/static/v1?label=glide&message=4.11.0&color=00C4CC)]()
[![Mockk](https://img.shields.io/static/v1?label=mockk&message=1.10.0&color=9F55FF)]()
[![Badge](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)]()

## Descrição do Projeto

- O Events Sicredi é um APP criado para demonstação de algumas bibliotecas e implementações do Android.

<p align="center">
 <a href="#começando">Começando</a> •
 <a href="#features">Features</a> • 
 <a href="#arquitetura">Arquitetura</a> • 
 <a href="#testes">Testes</a> • 
 <a href="#tecnologias">Tecnologias</a> • 
 <a href="#preview">Preview</a>
</p>

## Começando

- Para executar o projeto, será necessário instalar o [Android Studio](https://developer.android.com/studio/install?hl=pt-br&authuser=1).
- E para iniciar o desenvolvimento, é necessário clonar o projeto do GitHub num diretório de sua preferência:

```shell
cd "diretorio de sua preferencia"
git clone https://github.com/ArthurLDS/events-android.git
```

## Features

- O APP apresenta uma lista de eventos.
- Também apresenta os detalhes de um evento específico, onde o usuário pode fazer o check in  e também compartilhar o evento com outras pessoas em outros aplicativos
- Implementação de injeção de dependências usando `Koin`
- Cobertura de testes unitários dos repositorios e das view models usando a biblioteca `Mockk`
- O Código foi escrito buscando manter as melhores práticas de Clean Code

## Arquitetura

- O aplicativo usa **MVVM** como arquitetura, conforme recomendação do próprio Google.
<img src="imgs/MVVM.png"  width="800" height="200">

## Testes

- Para rodar os testes basta abrir o projeto no Android Studio.
- Logo em seguida navegue até a pasta `com.secredi.sicredipostapp`
- Clique com o botão direito sobre a pasta e depois em `Run 'Tests in 'sicredipostapp''`
<img src="imgs/testes1.png"  width="300" height="300">

## Tecnologias

As seguintes bibliotecas e frameworks foram usadas na construção do projeto:
- [Koin](https://github.com/InsertKoinIO/koin)
- [Glide](https://github.com/bumptech/glide)
- [Retrofit](https://github.com/square/retrofit)
- [Mockk](https://github.com/mockk/mockk)
- [AndroidX](https://developer.android.com/jetpack/androidx?authuser=1)

## Preview
<img src="imgs/part1.gif"  width="270" height="500"> <img src="imgs/part2.gif"  width="270" height="500">
