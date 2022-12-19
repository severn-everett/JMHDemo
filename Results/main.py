import argparse
import csv

import matplotlib.pyplot as plt

headers = []
throughput = []
err_range = []


def add_value(lc, c):
    if lc == 1:
        headers.append(c)
    elif lc == 2:
        throughput.append(float(c))
    elif lc == 3:
        err_range.append(float(c))


def populate_data(data_file):
    line_count = 1
    with open(data_file) as csvfile:
        line_reader = csv.reader(csvfile)
        for row in line_reader:
            if line_count > 3:
                break
            for cell in row:
                add_value(line_count, cell)
            line_count += 1


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Create a bar chart from results.')
    parser.add_argument('-t', '--title', type=str, help='the chart title')
    parser.add_argument('-y', '--y_axis', type=str, help='the y-axis label')
    parser.add_argument('-f', '--file', type=str, help='the data file')
    args = parser.parse_args()

    populate_data(args.file)

    ax = plt.subplots()[1]
    if len(err_range) != 0:
        plt.bar(headers, throughput, yerr=err_range, capsize=5)
    else:
        plt.bar(headers, throughput)

    plt.title(args.title)
    plt.ylabel(args.y_axis)
    plt.gca().get_yaxis().get_major_formatter().set_scientific(False)
    labels = ax.get_xticklabels()
    plt.setp(labels, rotation=45, horizontalalignment='right')
    plt.show()
