
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

Add these to your gradle files;

```bash
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```bash
  dependencies {
	    implementation 'com.github.BerkkanB:ComposeProgress:Tag'
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

![Uygulama Ekran Görüntüsü](https://via.placeholder.com/468x300?text=App+Screenshot+Here)
#### Basic BarProgress without any indicator and Icon

![Uygulama Ekran Görüntüsü](https://via.placeholder.com/468x300?text=App+Screenshot+Here)
#### Basic BarProgress with indicators

![Uygulama Ekran Görüntüsü](https://via.placeholder.com/468x300?text=App+Screenshot+Here)
#### Basic BarProgress with indicators and icon

![Uygulama Ekran Görüntüsü](https://via.placeholder.com/468x300?text=App+Screenshot+Here)
#### Animated BarProgress with indicators and icon

![Uygulama Ekran Görüntüsü](https://via.placeholder.com/468x300?text=App+Screenshot+Here)
#### Basic StepProgress without any indicator and Icon

  
