from airflow import DAG
from airflow.operators.bash_operator import BashOperator
from airflow.operators.python_operator import PythonOperator
from airflow.macros import ds_add
import pendulum

import os
import pandas as pd
from pathlib import Path

with DAG(
    dag_id = 'dados_climaticos',
    start_date = pendulum.datetime(2022, 10, 19, tz = "UTC"),
    schedule_interval = '0 0 * * 1', # executar toda segunda-feira
) as dag:

    tarefa_1 = BashOperator(
        task_id = 'cria_pasta',
        bash_command = 'mkdir -p "/home/caro/airflow/airflowhome/dados_teste/semana_{{data_interval_end.strftime("%Y-%m-%d")}}"'
    )

    def extrai_dados(data_interval_end):
        city = 'Boston'
        key = 'Z2UYXL4ACKW897Z6UD4EVTTSY'

        URL = os.path.join('https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/',
                f'{city}/{data_interval_end}/{ds_add(data_interval_end, 7)}?unitGroup=metric&include=days&key={key}&contentType=csv')

        dados = pd.read_csv(URL)

        file_path = Path(f"/home/caro/airflow/airflowhome/dados_teste/semana_{data_interval_end}")

        dados.to_csv(f'{file_path}/dados_brutos.csv')
        dados[['datetime', 'tempmin', 'temp', 'tempmax']].to_csv(f'{file_path}/temperaturas.csv')
        dados[['datetime', 'description', 'icon']].to_csv(f'{file_path}/condicoes.csv')


    tarefa_2 = PythonOperator(
        task_id = 'extrai_dados',
        python_callable = extrai_dados,
        op_kwargs = {'data_interval_end': '{{data_interval_end.strftime("%Y-%m-%d")}}'}
    )

    tarefa_1 >> tarefa_2