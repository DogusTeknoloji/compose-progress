[![](https://jitpack.io/v/DogusTeknoloji/ComposeProgress.svg)](https://jitpack.io/#BerkkanB/ComposeProgress)
# Jetpack Compose Progress

This library aims to develop multiple customizable types of progress components.


## Key Features

- Fully compose infrastructure 
- Instantly tracked state structure
- Lightweight with its pure component structure
- Highly customizable
- Animation option included in components
- Continuous development

  
## Add Your Project

Add it in your root build.gradle at the end of repositories:

```bash
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency:

```bash
  dependencies {
	    implementation 'com.github.DogusTeknoloji:ComposeProgress:Tag'
	}
```

  
## How to Use

#### Animated BarProgress
```Kotlin
fun BarProgress(
    modifier: Modifier,
    progressColor: Color,
    progressBackgroundColor: Color,
    fillProgressBy: Float,
    indicators: List<Float>? = null,
    indicatorColor: Color = Color.White,
    indicatorWidth: Float = 5f,
    cornerRadius: CornerRadius = CornerRadius.Zero,
    stepImageVector: ImageVector? = null,
    stepImageVectorMultiplier: Float = 0.6f,
    animationDuration: Int,
    animationDelay: Int = 0
)
```
#### Simple BarProgress

```Kotlin
fun BarProgress(
    modifier: Modifier,
    progressColor: Color,
    progressBackgroundColor: Color,
    fillProgressBy: Float,
    indicators: List<Float>? = null,
    indicatorColor: Color = Color.White,
    indicatorWidth: Float = 5f,
    cornerRadius: CornerRadius = CornerRadius.Zero,
    stepImageVector: ImageVector? = null,
    stepImageVectorMultiplier: Float = 0.6f
)
```

#### StepProgress

```Kotlin
fun StepProgress(
    modifier: Modifier,
    progressColor: Color,
    progressBackgroundColor: Color,
    currentStep: Int,
    totalStep: Int,
    indicatorColor: Color = Color.White,
    indicatorWidth: Float = 5f,
    cornerRadius: CornerRadius = CornerRadius.Zero,
    stepImageVector: ImageVector? = null,
    stepImageVectorMultiplier: Float = 0.6f
)
```



  
## Bar Progress

<img src="https://github.com/DogusTeknoloji/compose-progress/blob/master/ss/ss3.jpg" width="300">

#### Basic BarProgress without any indicator and Icon

<img src="https://github.com/DogusTeknoloji/compose-progress/blob/master/ss/ss2.jpg" width="300">

#### Basic BarProgress with indicators

<img src="https://github.com/DogusTeknoloji/compose-progress/blob/master/ss/Screenrecorder-2022-11-18-11-19-58-608.gif" width="300">

#### Animated BarProgress with indicators and icon

## Step Progress

<img src="https://github.com/DogusTeknoloji/compose-progress/blob/master/ss/ss.jpg" width="300">

#### Basic StepProgress with Icon

  
## Note that

You can create any shape you want with modifier parameter, these screenshots are just basic samples.

## What is next ?

- Animated StepBar
- Different types of progress
- More customizable components
