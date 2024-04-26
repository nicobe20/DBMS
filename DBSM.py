#funciones de crud de la db
import threading
import subprocess
import multiprocessing #maybe


class ThreadSafeDB:
    def __init__(self):
        self.records = []
        self.lock = threading.RLock()

    def add_record(self, record):
        with self.lock:
            self.records.append(record)

    def list_records(self):
        with self.lock:
            return self.records.copy()

    def update_record(self, record_id, new_record):
        with self.lock:
            self.records[record_id] = new_record

    def delete_record(self, record_id):
        with self.lock:
            del self.records[record_id]

    def save_to_file(self, filename):
        with self.lock:
            with open(filename, 'w') as f:
                for record in self.records:
                    f.write(','.join(map(str, record)) + '\n')

'''
#Como adquirir los strings de la consola de karelRobot? puede ser con subproces.pip pero no se ajaj.
import subprocess

# Define the command and parameters you want to run
cmd = ['your_program', 'arg1', 'arg2']

# Start the process and capture its output
process = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)

# Read the output line by line
while True:
    output = process.stdout.readline()
    if output == '' and process.poll() is not None:
        break
    if output:
        print(output.strip())  # Process or display the output

# Check for any errors
err = process.stderr.read()
if err:
    print("Error:", err)

'''