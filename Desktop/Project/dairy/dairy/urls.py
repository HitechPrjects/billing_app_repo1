"""
URL configuration for dairy project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from myapp import views
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    path('admin/', admin.site.urls),
    path('',views.homepage,name='homepage'),
    path('patient_register/',views.patient_register.as_view(), name='patient_register'),
    
    path('doctor_register/',views.doctor_register.as_view(), name='doctor_register'),
    path('login/',views.login_request, name='login'),
    path('alogin/',views.adlogin_request, name='alogin'),
    
    path('logout/',views.logout_view, name='logout'),
]+static(settings.MEDIA_URL,document_root=settings.MEDIA_ROOT)

