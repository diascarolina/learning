import os
import pandas as pd
from datetime import datetime, timedelta
from pathlib import Path

# Intervalo de datas
data_inicio = datetime.today()
data_fim = data_inicio + timedelta(days=7)

# Formatando as datas
data_inicio = data_inicio.strftime('%Y-%m-%d')
data_fim = data_fim.strftime('%Y-%m-%d')

city = 'Boston'
key = os.getenv('VISUAL_CROSSING_KEY')

URL = os.path.join('https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/',
        f'{city}/{data_inicio}/{data_fim}?unitGroup=metric&include=days&key={key}&contentType=csv')

dados = pd.read_csv(URL)
print(dados.head())

file_path = Path(f"curso-airflow-alura/data/semana_{data_inicio}/")

if not os.path.exists(file_path):
  os.makedirs(file_path)

dados.to_csv(f'{file_path}/dados_brutos.csv')
dados[['datetime', 'tempmin', 'temp', 'tempmax']].to_csv(f'{file_path}/temperaturas.csv')
dados[['datetime', 'description', 'icon']].to_csv(f'{file_path}/condicoes.csv')